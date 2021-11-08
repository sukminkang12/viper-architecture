package com.github.sukminkang12.viperarchitecture.listeners

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.github.sukminkang12.viperarchitecture.services.MyProjectService

internal class MyProjectManagerListener : ProjectManagerListener {

    //Android Studio에서 프로젝트를 열 때마다 해당 fun이 호출됨
    override fun projectOpened(project: Project) {
        projectInstance = project
        project.service<MyProjectService>()
    }

    //Android Studio에서 프로젝트를 닫을 때마다 해당 fun이 호출됨
    override fun projectClosing(project: Project) {
        projectInstance = null
        super.projectClosing(project)
    }

    companion object {
        //VirtaulFile 을 가져오기 위해 사용 되는데 프로젝트 네임이 같아야 사용 가능하게 만듦
        var projectInstance: Project? = null
    }
}