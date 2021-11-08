package com.github.sukminkang12.viperarchitecture.wizard

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.sukminkang12.viperarchitecture.wizard.template.viperWizardTemplate

class WizardTemplateProviderImpl  : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(viperWizardTemplate)
}