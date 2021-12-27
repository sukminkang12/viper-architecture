package com.github.sukminkang12.viperarchitecture.wizard.template

import android.databinding.tool.ext.toCamelCase
import com.android.tools.idea.wizard.template.ProjectTemplateData
import com.github.sukminkang12.viperarchitecture.extensions.toUnderBarCase

fun manifestTemplateXml(
    packageName: String,
    entityName: String
) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
package="com.qarasoft.owwl">
    <application>
        <activity android:name="$packageName.${entityName}Activity"
            android:screenOrientation="portrait" />   
    </application>
</manifest>
        """

fun makeActivity(
    packageName: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
) = """package $packageName
import android.os.Bundle
import com.qarasoft.owwl.databinding.${layoutName.toCamelCase()}Binding

class ${entityName}Activity : ${entityName}ViewProtocol() {
    private val TAG = this.javaClass.simpleName
    private lateinit var binding: ${layoutName.toCamelCase()}Binding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ${layoutName.toCamelCase()}Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
""".trimIndent()

fun makePresenter(
    packageName: String,
    entityName: String
) = """package $packageName
import android.content.Context

class ${entityName}Presenter(context: Context): 
    ${entityName}PresenterProtocol(context) {
    
    private val TAG = this.javaClass.simpleName
}
""".trimIndent()

fun makeProtocol(
    packageName: String,
    entityName: String
) = """package $packageName
import android.content.Context
import android.content.Intent
import com.qarasoft.owwl.base.BaseActivity
import com.qarasoft.owwl.base.BaseFragment
import com.qarasoft.owwl.base.BasePresenter
import com.qarasoft.owwl.base.BaseRouter
import com.qarasoft.owwl.common.RequestCode

fun BaseRouter.present${entityName}() {
    val intent = Intent(requireContext(), ${entityName}Activity::class.java)
    startActivity(intent, RequestCode.${entityName.toUnderBarCase()})
}

abstract class ${entityName}ViewProtocol: 
    BaseActivity<${entityName}PresenterProtocol>() {
    
    override fun createPresenter() {
        val router = ${entityName}Router(activity = this)
        presenter = ${entityName}Presenter(this).apply {
            this.router = router
        }
    }
}

abstract class ${entityName}PresenterProtocol(context: Context) :
    BasePresenter(context) {
       
    var router: ${entityName}RouterProtocol? = null
    
}

abstract class ${entityName}RouterProtocol(activity: BaseActivity<*>? = null, fragment: BaseFragment<*>? = null) :
    BaseRouter(activity, fragment)

""".trimIndent()

fun makeRouter(
    packageName: String,
    entityName: String
) = """package $packageName
import com.qarasoft.owwl.base.BaseActivity
import com.qarasoft.owwl.base.BaseFragment

class ${entityName}Router(activity: BaseActivity<*>? = null, fragment: BaseFragment<*>? = null) :
    ${entityName}RouterProtocol(activity, fragment)
""".trimIndent()


fun makeActivityLayout(
) = """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
</androidx.constraintlayout.widget.ConstraintLayout>
"""


//fun makeFragment(
//    date: String,
//    defaultPackage: String,
//    newFilePackage: String,
//    entityName: String,
//    layoutName: String,
//    projectData: ProjectTemplateData
//): String {
//    val databindingName = "Fragment${entityName.toLowerCase().toCamelCase()}Binding"
//    return """package $newFilePackage
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.viewModels
//import dagger.hilt.android.AndroidEntryPoint
//import ${defaultPackage}.presentation.base.BaseAppBarFragment
//import ${defaultPackage}.databinding.${databindingName}
//import ${defaultPackage}.R
///**
// * Created by okwon on ${date}.
// * Description :
// */
//@AndroidEntryPoint
//class ${entityName}Fragment : BaseAppBarFragment<${databindingName}>(R.layout.${layoutName.toLowerCase()}) {
//    private val viewModel by viewModels<${entityName}ViewModel>()
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.fragment = this
//        binding.viewModel = viewModel
//        super.onViewCreated(view, savedInstanceState)
//    }
//    override fun onInitViews() {}
//    override fun onSubscribeVm() {}
//    override fun onSetToolBarViews() {}
//}
//"""
//}

fun makeFragmentLayout(
    defaultPackage: String,
    newFilePackage: String,
    entityName: String) = """<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="fragment"
            type="${newFilePackage}.${entityName}Fragment" />
        <variable
            name="viewModel"
            type="${newFilePackage}.${entityName}ViewModel" />
    </data>
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""