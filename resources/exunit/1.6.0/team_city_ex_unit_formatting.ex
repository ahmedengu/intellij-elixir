# Originally based on https://github.com/lixhq/teamcity-exunit-formatter, but it did not work for parallel tests: IDEA
# does not honor flowId, so needed to use the nodeId/parentNodeIde system
#
# nodeId and parentNodeId system is documented in
# https://intellij-support.jetbrains.com/hc/en-us/community/posts/115000389550/comments/115000330464
defmodule TeamCityExUnitFormatting do
  # Constants

  @root_parent_node_id 0

  # Struct

  defstruct failures_counter: 0,
            invalids_counter: 0,
            seed: nil,
            skipped_counter: 0,
            tests_counter: 0,
            trace: false,
            width: 80

  # Functions

  def new(opts) do
    %__MODULE__{
      seed: opts[:seed],
      trace: opts[:trace]
    }
  end

  # ExUnit.TestCase is deprecated in 1.6.0 and replaced by ExUnit.TestModule.  ExUnit.TestCase will be removed in 2.0.0.
  def put_event(state = %__MODULE__{}, {:case_finished, %ExUnit.TestCase{}}), do: state
  def put_event(state = %__MODULE__{}, {:case_started, %ExUnit.TestCase{}}), do: state

  def put_event(state = %__MODULE__{}, {:module_finished, test_module = %ExUnit.TestModule{}}) do
    put_formatted(:test_suite_finished, attributes(test_module))

    state
  end

  def put_event(state = %__MODULE__{}, {:module_started, test_module = %ExUnit.TestModule{}}) do
    put_formatted(:test_suite_started, attributes(test_module))

    state
  end

  def put_event(state = %__MODULE__{}, {:suite_finished, _run_us, _load_us}), do: state

  def put_event(state = %__MODULE__{}, {:suite_started, opts}) do
    seed = opts[:seed]

    IO.puts("Suite started with seed #{seed}")

    %__MODULE__{state | seed: seed, trace: opts[:trace]}
  end

  def put_event(
        state = %__MODULE{
          failures_counter: failures_counter,
          tests_counter: tests_counter,
          width: width
        },
        {
          :test_finished,
          test = %ExUnit.Test{
            logs: logs,
            state:
              failed = {
                :failed,
                {_, _reason, _}
              },
            time: time
          }
        }
      ) do
    updated_failures_counter = failures_counter + 1
    attributes = attributes(test)

    formatted_failure =
      ExUnit.Formatter.format_test_failure(
        test,
        failed,
        updated_failures_counter,
        width,
        &formatter/2
      )

    details = IO.iodata_to_binary([formatted_failure, format_logs(logs)])

    put_formatted(
      :test_failed,
      Keyword.merge(
        attributes,
        details: details,
        message: ""
      )
    )

    put_formatted(
      :test_finished,
      Keyword.merge(
        attributes,
        duration: div(time, 1000)
      )
    )

    %{
      state
      | tests_counter: tests_counter + 1,
        failures_counter: updated_failures_counter
    }
  end

  def put_event(
        state = %__MODULE__{
          failures_counter: failures_counter,
          width: width,
          tests_counter: tests_counter
        },
        {:test_finished, test = %ExUnit.Test{logs: logs, state: {:failed, failed}, time: time}}
      )
      when is_list(failed) do
    updated_failures_counter = failures_counter + 1
    attributes = attributes(test)

    formatted_failure =
      ExUnit.Formatter.format_test_failure(
        test,
        failed,
        updated_failures_counter,
        width,
        &formatter/2
      )

    details = IO.iodata_to_binary([formatted_failure, format_logs(logs)])

    put_formatted(
      :test_failed,
      Keyword.merge(
        attributes,
        details: details,
        message: ""
      )
    )

    put_formatted(
      :test_finished,
      Keyword.merge(
        attributes,
        duration: div(time, 1000)
      )
    )

    %{
      state
      | tests_counter: tests_counter + 1,
        failures_counter: updated_failures_counter
    }
  end

  def put_event(
        state = %__MODULE__{
          tests_counter: tests_counter,
          skipped_counter: skipped_counter
        },
        {:test_finished, test = %ExUnit.Test{state: {ignored, _}}}
      ) when ignored in ~w(excluded skip skipped)a do
    attributes = attributes(test)

    put_formatted(:test_ignored, attributes)
    put_formatted(:test_finished, attributes)

    %{
      state
      | tests_counter: tests_counter + 1,
        skipped_counter: skipped_counter + 1
    }
  end

  def put_event(
        state = %__MODULE__{},
        {
          :test_finished,
          test = %ExUnit.Test{
            time: time
          }
        }
      ) do
    put_formatted(
      :test_finished,
      test
      |> attributes()
      |> Keyword.merge(duration: div(time, 1000))
    )

    state
  end

  def put_event(state = %__MODULE__{}, {:test_started, test = %ExUnit.Test{tags: tags}}) do
    put_formatted(
      :test_started,
      test
      |> attributes()
      |> Keyword.merge(locationHint: "file://#{tags[:file]}:#{tags[:line]}")
    )

    state
  end

  def put_event(state = %__MODULE__{}, event) do
    IO.warn(
      "#{inspect(__MODULE__)} does not know how to process event (#{inspect(event)}).  " <>
        "Please report this message to https://github.com/KronicDeth/intellij-elixir/issues/new."
    )

    state
  end

  ## Private Functions

  defp attributes(test_or_test_module) do
    [
      nodeId: nodeId(test_or_test_module),
      name: name(test_or_test_module),
      parentNodeId: parentNodeId(test_or_test_module)
    ]
  end

  defp camelize(s) do
    [head | tail] = String.split(s, "_")
    "#{head}#{Enum.map(tail, &String.capitalize/1)}"
  end

  @colors [
     diff_delete: :red,
     diff_delete_whitespace: IO.ANSI.color_background(2, 0, 0),
     diff_insert: :green,
     diff_insert_whitespace: IO.ANSI.color_background(0, 2, 0)
  ]

  defp colorize(escape, string) do
    [escape, string, :reset]
    |> IO.ANSI.format_fragment(true)
    |> IO.iodata_to_binary()
  end

  defp colorize_doc(escape, doc) do
    Inspect.Algebra.color(doc, escape, %Inspect.Opts{syntax_colors: @colors})
  end

  # Must escape certain characters
  # see: https://confluence.jetbrains.com/display/TCD9/Build+Script+Interaction+with+TeamCity
  defp escape_output(s) when not is_binary(s), do: escape_output("#{s}")

  defp escape_output(s) do
    s
    |> String.replace("|", "||")
    |> String.replace("'", "|'")
    |> String.replace("\n", "|n")
    |> String.replace("\r", "|r")
    |> String.replace("[", "|[")
    |> String.replace("]", "|]")
  end

  defp format(type, attributes) do
    messageName =
      type
      |> Atom.to_string()
      |> camelize()

    attrs =
      attributes
      |> Enum.map(&format_attribute/1)
      |> Enum.join(" ")

    "##teamcity[#{messageName} #{attrs}]"
  end

  defp format_attribute({k, v}) do
    "#{Atom.to_string(k)}='#{escape_output(v)}'"
  end

  defp format_logs(""), do: ""

  defp format_logs(logs) do
    indent = "\n     "
    indented_logs = String.replace(logs, "\n", indent)
    [indent, "The following output was logged:", indent | indented_logs]
  end

  defp format_module_name(module_name) do
    module_name
    |> to_string()
    |> String.replace(~r/\bElixir\./, "")
  end

  defp formatter(:diff_enabled?, _), do: true

  defp formatter(:error_info, msg), do: colorize(:red, msg)

  defp formatter(:extra_info, msg), do: colorize(:cyan, msg)

  defp formatter(:location_info, msg), do: colorize([:bright, :black], msg)

  defp formatter(:diff_delete, doc), do: colorize_doc(:diff_delete, doc)

  defp formatter(:diff_delete_whitespace, doc), do: colorize_doc(:diff_delete_whitespace, doc)

  defp formatter(:diff_insert, doc), do: colorize_doc(:diff_insert, doc)

  defp formatter(:diff_insert_whitespace, doc), do: colorize_doc(:diff_insert_whitespace, doc)

  defp formatter(:blame_diff, msg), do: colorize(:red, msg)

  defp formatter(_, msg), do: msg

  defp name(test = %ExUnit.Test{name: name}) do
    named_captures =
      Regex.named_captures(
        ~r|test doc at (?<module>.+)\.(?<function>\w+)/(?<arity>\d+) \((?<count>\d+)\)|,
        to_string(name)
      )

    name(test, named_captures)
  end

  defp name(%ExUnit.TestModule{name: name}), do: format_module_name(name)

  defp name(%ExUnit.Test{name: name}, nil), do: to_string(name)

  defp name(
         %ExUnit.Test{module: module_name},
         %{"arity" => arity, "count" => count, "function" => function, "module" => module}
       ) do
    name = "#{function}/#{arity} doc (#{count})"

    if module <> "Test" == format_module_name(module_name) do
      name
    else
      "#{module}.#{name}"
    end
  end

  defp nodeId(%ExUnit.Test{module: module_name, name: name}), do: "#{module_name}.#{name}"
  defp nodeId(%ExUnit.TestModule{name: name}), do: name

  defp parentNodeId(%ExUnit.Test{module: module_name}), do: module_name
  defp parentNodeId(%ExUnit.TestModule{}), do: @root_parent_node_id

  # DO NOT use `flowId` as an attribute.  IDEA ignores flowId and so it can't be used to interleave async test output
  defp put_formatted(type, attributes) do
    type
    |> format(attributes)
    |> IO.puts()
  end
end
