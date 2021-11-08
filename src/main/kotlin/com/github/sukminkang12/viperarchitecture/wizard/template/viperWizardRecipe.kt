package com.github.sukminkang12.viperarchitecture.wizard.template

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.sukminkang12.viperarchitecture.extensions.save
import com.github.sukminkang12.viperarchitecture.listeners.MyProjectManagerListener.Companion.projectInstance
import com.intellij.openapi.roots.ProjectRootManager
import java.io.File
import com.intellij.openapi.roots.*
import com.intellij.psi.*
import java.util.*

fun RecipeExecutor.viperSetup(
    moduleData: ModuleTemplateData,
    defaultPackage: String,
    newFilePackage: String,
    entityName: String,
    layoutName: String
) {
    val (projectData, _, _, manifestOut) = moduleData
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.firstOrNull { it.path.contains("app/src/main/java") }?:return
    val virtRes = virtualFiles.firstOrNull { it.path.contains("app/src/main/res") }?:return
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)?:return
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)?:return

    val activityClass = "${entityName}Activity".capitalize()
    val presenterClass = "${entityName}Presenter".capitalize()
    val routerClass = "${entityName}Router".capitalize()
    val protocolClass = "${entityName}Protocol".capitalize()

    mergeXml(
        manifestTemplateXml(defaultPackage, entityName),
        manifestOut.resolve("AndroidManifest.xml")
    )

    makeActivity(defaultPackage, entityName, layoutName, projectData)
        .save(directorySrc, "$defaultPackage", "$activityClass.kt")

    makePresenter(defaultPackage, entityName)
        .save(directorySrc, "$defaultPackage", "$presenterClass.kt")

    makeRouter(defaultPackage, entityName)
        .save(directorySrc, "$defaultPackage", "$routerClass.kt")

    makeProtocol(defaultPackage, entityName)
        .save(directorySrc, "$defaultPackage", "$protocolClass.kt")

    makeActivityLayout()
        .save(directoryRes, "layout","${layoutName}.xml")

    //    save(
//        makeFragment(date, defaultPackage, newFilePackage, entityName, layoutName, projectData),
//        srcKotlinDir.resolve("${entityName}Fragment.kt")
//    )
}