package com.github.sukminkang12.viperarchitecture.services

import com.intellij.openapi.project.Project
import com.github.sukminkang12.viperarchitecture.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
