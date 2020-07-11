// This is a generated file. Not intended for manual editing.
package org.elixir_lang.psi.impl;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.elixir_lang.psi.ElixirNoParenthesesManyStrictNoParenthesesExpression;
import org.elixir_lang.psi.ElixirUnqualifiedNoParenthesesManyArgumentsCall;
import org.elixir_lang.psi.ElixirVisitor;
import org.jetbrains.annotations.NotNull;

public class ElixirNoParenthesesManyStrictNoParenthesesExpressionImpl extends ASTWrapperPsiElement implements ElixirNoParenthesesManyStrictNoParenthesesExpression {

  public ElixirNoParenthesesManyStrictNoParenthesesExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElixirVisitor visitor) {
    visitor.visitNoParenthesesManyStrictNoParenthesesExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElixirVisitor) accept((ElixirVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ElixirUnqualifiedNoParenthesesManyArgumentsCall getUnqualifiedNoParenthesesManyArgumentsCall() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, ElixirUnqualifiedNoParenthesesManyArgumentsCall.class));
  }

  @Override
  @NotNull
  public OtpErlangObject quote() {
    return ElixirPsiImplUtil.quote(this);
  }

}
