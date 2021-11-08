package com.github.sukminkang12.viperarchitecture.extensions

import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import org.jetbrains.kotlin.idea.KotlinLanguage

fun String.toSnakeCase() = replace(humps, "_").toLowerCase()
private val humps = "(?<=.)(?=\\p{Upper})".toRegex()


fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    val any = try {
        val destDir = subDirPath.split(".").toDir(srcDir)
        val psiFile = PsiFileFactory
            .getInstance(srcDir.project)
            .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
        destDir.add(psiFile)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}

fun String.toUnderBarCase() : String{
    var ret = ""

    for (i in this.indices) {
        if (i != 0 && this[i].isUpperCase()) {
            ret += "_${this[i]}"
        } else {
            ret += "${this[i].toUpperCase()}"
        }
    }

    return ret
}