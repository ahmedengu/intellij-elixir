package org.elixir_lang.reference.resolver

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.impl.source.resolve.ResolveCache
import org.elixir_lang.Reference.forEachNavigationElement
import org.elixir_lang.navigation.isDecompiled
import org.elixir_lang.psi.scope.module.MultiResolve
import org.elixir_lang.reference.module.ResolvableName.resolvableName


object Module : ResolveCache.PolyVariantResolver<org.elixir_lang.reference.Module> {
    override fun resolve(
            module: org.elixir_lang.reference.Module,
            incompleteCode: Boolean
    ): Array<PsiElementResolveResult> =
            module.element.let { element ->
                resolvableName(element)?.let { name ->
                    val sameFileResolveResultList =
                            MultiResolve.resolveResults(name, incompleteCode, element)

                    if (sameFileResolveResultList.isNotEmpty()) {
                        sameFileResolveResultList
                    } else {
                        multiResolveProject(
                                element.project,
                                name
                        )
                    }
                } ?: emptyArray()
            }

    private fun multiResolveProject(project: Project,
                                    name: String): Array<PsiElementResolveResult> {
        val resolveResultList = mutableListOf<PsiElementResolveResult>()

        forEachNavigationElement(
                project,
                name
        ) { navigationElement ->
            resolveResultList.add(PsiElementResolveResult(navigationElement))

            true
        }

        val sourceResolveResultList = resolveResultList.filter { !it.element.isDecompiled() }

        return if (sourceResolveResultList.isNotEmpty()) {
            sourceResolveResultList.toTypedArray()
        } else {
            resolveResultList.toTypedArray()
        }
    }
}
