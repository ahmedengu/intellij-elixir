// This is a generated file. Not intended for manual editing.
package org.elixir_lang.psi.impl;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.elixir_lang.psi.ElixirMapArguments;
import org.elixir_lang.psi.ElixirMapConstructionArguments;
import org.elixir_lang.psi.ElixirMapUpdateArguments;
import org.elixir_lang.psi.ElixirVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ElixirMapArgumentsImpl extends ASTWrapperPsiElement implements ElixirMapArguments {

  public ElixirMapArgumentsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElixirVisitor visitor) {
    visitor.visitMapArguments(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElixirVisitor) accept((ElixirVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ElixirMapConstructionArguments getMapConstructionArguments() {
    return PsiTreeUtil.getChildOfType(this, ElixirMapConstructionArguments.class);
  }

  @Override
  @Nullable
  public ElixirMapUpdateArguments getMapUpdateArguments() {
    return PsiTreeUtil.getChildOfType(this, ElixirMapUpdateArguments.class);
  }

  @Override
  @NotNull
  public OtpErlangObject quote() {
    return ElixirPsiImplUtil.quote(this);
  }

}
