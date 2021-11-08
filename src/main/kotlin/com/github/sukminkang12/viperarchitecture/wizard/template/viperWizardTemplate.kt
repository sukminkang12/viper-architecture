package com.github.sukminkang12.viperarchitecture.wizard.template

import com.android.tools.idea.wizard.template.*
import com.github.sukminkang12.viperarchitecture.extensions.toSnakeCase

private const val MIN_SDK = 23

val viperWizardTemplate
    get() = template {
        name = "Android VIPER creator"
        description = "Creates a new Activity, Presenter, Router, Protocol with layout file."
        minApi = MIN_SDK
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule)

        val packageNameParam = defaultPackageNameParameter
        val pathNameParam = pathNameParameter
        val entityName = stringParameter {
            name = "Entity Name (Must Be CamelCase)"
            default = ""
            help = "The name of the entity class to create and use in activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = ""
            help = "The name of the layout to create for the activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(entityName.value.toSnakeCase())}" }
        }

        widgets(
            TextFieldWidget(packageNameParam),
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(pathNameParam)
        )

        recipe = { data: TemplateData ->
            viperSetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                pathNameParam.value,
                entityName.value,
                layoutName.value
            )
        }
    }

val defaultPackageNameParameter get() = stringParameter {
    name = "Default Package"
    visible = { !isNewModule }
    default = "com.mycompany.myapp"
    constraints = listOf(Constraint.PACKAGE)
    suggest = { packageName }
}

val pathNameParameter get() = stringParameter {
    name = "New File Location"
    visible = { !isNewModule }
    default = "com.mycompany.myapp."
    constraints = listOf(Constraint.PACKAGE)
    suggest = { "$packageName" }
}