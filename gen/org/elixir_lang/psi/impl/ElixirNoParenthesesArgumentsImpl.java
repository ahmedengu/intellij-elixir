// This is a generated file. Not intended for manual editing.
package org.elixir_lang.psi.impl;

import com.ericsson.otp.erlang.OtpErlangObject;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.elixir_lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElixirNoParenthesesArgumentsImpl extends ASTWrapperPsiElement implements ElixirNoParenthesesArguments {

  public ElixirNoParenthesesArgumentsImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElixirVisitor visitor) {
    visitor.visitNoParenthesesArguments(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElixirVisitor) accept((ElixirVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElixirEmptyParentheses> getEmptyParenthesesList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElixirEmptyParentheses.class);
  }

  @Override
  @NotNull
  public List<ElixirMatchedExpression> getMatchedExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElixirMatchedExpression.class);
  }

  @Override
  @Nullable
  public ElixirNoParenthesesKeywords getNoParenthesesKeywords() {
    return PsiTreeUtil.getChildOfType(this, ElixirNoParenthesesKeywords.class);
  }

  @Override
  @NotNull
  public List<ElixirNoParenthesesManyStrictNoParenthesesExpression> getNoParenthesesManyStrictNoParenthesesExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElixirNoParenthesesManyStrictNoParenthesesExpression.class);
  }

  @Override
  @Nullable
  public ElixirNoParenthesesOneArgument getNoParenthesesOneArgument() {
    return PsiTreeUtil.getChildOfType(this, ElixirNoParenthesesOneArgument.class);
  }

  @Override
  @NotNull
  public OtpErlangObject[] quoteArguments() {
    return ElixirPsiImplUtil.quoteArguments(this);
  }

}
