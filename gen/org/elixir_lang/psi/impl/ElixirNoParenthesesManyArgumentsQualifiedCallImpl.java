// This is a generated file. Not intended for manual editing.
package org.elixir_lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import org.elixir_lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElixirNoParenthesesManyArgumentsQualifiedCallImpl extends ASTWrapperPsiElement implements ElixirNoParenthesesManyArgumentsQualifiedCall {

  public ElixirNoParenthesesManyArgumentsQualifiedCallImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElixirVisitor) ((ElixirVisitor)visitor).visitNoParenthesesManyArgumentsQualifiedCall(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ElixirAlias getAlias() {
    return findChildByClass(ElixirAlias.class);
  }

  @Override
  @Nullable
  public ElixirAtCharTokenOrNumberOperation getAtCharTokenOrNumberOperation() {
    return findChildByClass(ElixirAtCharTokenOrNumberOperation.class);
  }

  @Override
  @Nullable
  public ElixirAtom getAtom() {
    return findChildByClass(ElixirAtom.class);
  }

  @Override
  @Nullable
  public ElixirCaptureCharTokenOrNumberOperation getCaptureCharTokenOrNumberOperation() {
    return findChildByClass(ElixirCaptureCharTokenOrNumberOperation.class);
  }

  @Override
  @NotNull
  public List<ElixirCharListHeredoc> getCharListHeredocList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElixirCharListHeredoc.class);
  }

  @Override
  @NotNull
  public List<ElixirCharListLine> getCharListLineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElixirCharListLine.class);
  }

  @Override
  @NotNull
  public ElixirInfixDotOperator getInfixDotOperator() {
    return findNotNullChildByClass(ElixirInfixDotOperator.class);
  }

  @Override
  @Nullable
  public ElixirInterpolatedCharListSigilHeredoc getInterpolatedCharListSigilHeredoc() {
    return findChildByClass(ElixirInterpolatedCharListSigilHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirInterpolatedCharListSigilLine getInterpolatedCharListSigilLine() {
    return findChildByClass(ElixirInterpolatedCharListSigilLine.class);
  }

  @Override
  @Nullable
  public ElixirInterpolatedRegexHeredoc getInterpolatedRegexHeredoc() {
    return findChildByClass(ElixirInterpolatedRegexHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirInterpolatedSigilHeredoc getInterpolatedSigilHeredoc() {
    return findChildByClass(ElixirInterpolatedSigilHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirInterpolatedStringSigilHeredoc getInterpolatedStringSigilHeredoc() {
    return findChildByClass(ElixirInterpolatedStringSigilHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirInterpolatedWordsHeredoc getInterpolatedWordsHeredoc() {
    return findChildByClass(ElixirInterpolatedWordsHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirList getList() {
    return findChildByClass(ElixirList.class);
  }

  @Override
  @Nullable
  public ElixirLiteralCharListSigilHeredoc getLiteralCharListSigilHeredoc() {
    return findChildByClass(ElixirLiteralCharListSigilHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirLiteralRegexHeredoc getLiteralRegexHeredoc() {
    return findChildByClass(ElixirLiteralRegexHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirLiteralSigilHeredoc getLiteralSigilHeredoc() {
    return findChildByClass(ElixirLiteralSigilHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirLiteralStringSigilHeredoc getLiteralStringSigilHeredoc() {
    return findChildByClass(ElixirLiteralStringSigilHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirLiteralWordsHeredoc getLiteralWordsHeredoc() {
    return findChildByClass(ElixirLiteralWordsHeredoc.class);
  }

  @Override
  @Nullable
  public ElixirMatchedAtOperation getMatchedAtOperation() {
    return findChildByClass(ElixirMatchedAtOperation.class);
  }

  @Override
  @Nullable
  public ElixirMatchedNonNumericCaptureOperation getMatchedNonNumericCaptureOperation() {
    return findChildByClass(ElixirMatchedNonNumericCaptureOperation.class);
  }

  @Override
  @Nullable
  public ElixirMatchedUnaryOperation getMatchedUnaryOperation() {
    return findChildByClass(ElixirMatchedUnaryOperation.class);
  }

  @Override
  @Nullable
  public ElixirNoParenthesesManyArguments getNoParenthesesManyArguments() {
    return findChildByClass(ElixirNoParenthesesManyArguments.class);
  }

  @Override
  @Nullable
  public ElixirNoParenthesesNoArgumentsQualifiedCall getNoParenthesesNoArgumentsQualifiedCall() {
    return findChildByClass(ElixirNoParenthesesNoArgumentsQualifiedCall.class);
  }

  @Override
  @Nullable
  public ElixirNoParenthesesNoArgumentsUnqualifiedCallOrVariable getNoParenthesesNoArgumentsUnqualifiedCallOrVariable() {
    return findChildByClass(ElixirNoParenthesesNoArgumentsUnqualifiedCallOrVariable.class);
  }

  @Override
  @Nullable
  public ElixirNoParenthesesStrict getNoParenthesesStrict() {
    return findChildByClass(ElixirNoParenthesesStrict.class);
  }

  @Override
  @Nullable
  public ElixirNumber getNumber() {
    return findChildByClass(ElixirNumber.class);
  }

  @Override
  @Nullable
  public ElixirQualifiedAlias getQualifiedAlias() {
    return findChildByClass(ElixirQualifiedAlias.class);
  }

  @Override
  @NotNull
  public List<ElixirStringHeredoc> getStringHeredocList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElixirStringHeredoc.class);
  }

  @Override
  @NotNull
  public List<ElixirStringLine> getStringLineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElixirStringLine.class);
  }

  @Override
  @Nullable
  public ElixirUnaryCharTokenOrNumberOperation getUnaryCharTokenOrNumberOperation() {
    return findChildByClass(ElixirUnaryCharTokenOrNumberOperation.class);
  }

}
