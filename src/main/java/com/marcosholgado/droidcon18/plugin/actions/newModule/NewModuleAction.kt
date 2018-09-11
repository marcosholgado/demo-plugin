package com.marcosholgado.droidcon18.plugin.actions.newModule

import com.android.tools.idea.npw.module.ModuleGalleryEntry
import com.android.tools.idea.npw.module.NewModuleModel
import com.android.tools.idea.sdk.wizard.SdkQuickfixUtils
import com.android.tools.idea.templates.TemplateManager
import com.android.tools.idea.ui.wizard.StudioWizardDialogBuilder
import com.android.tools.idea.wizard.model.ModelWizard
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.android.sdk.AndroidSdkUtils
import java.util.ArrayList

class NewModuleAction: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        if (!AndroidSdkUtils.isAndroidSdkAvailable()) {
            SdkQuickfixUtils.showSdkMissingDialog()
            return
        }

        val template = TemplateManager.getExtraTemplateRootFolders()
        //val template = TemplateManager.getTemplateRootFolder()

        val moduleDescriptions = ArrayList<ModuleGalleryEntry>()
//        for (provider in ModuleDescriptionProvider.EP_NAME.extensions) {
//            moduleDescriptions.addAll(provider.descriptions)
//        }

        val provider = DroidconModuleProvider()

        moduleDescriptions.addAll(provider.descriptions)

        val module = NewModuleModel(event.project!!)

        val chooseModuleTypeStep = ChooseDroidconModuleStep(module, moduleDescriptions)

        val wizard = ModelWizard.Builder().addStep(chooseModuleTypeStep).build()

        StudioWizardDialogBuilder(wizard, "New Android Droidcon Module").setUseNewUx(true).build().show()
        //// THIS WORKS
//        val wizardModel = DroidWizardModel(event.project!!)
//        val stepA = FormStepA(wizardModel)
//        val wizard = ModelWizard.Builder().addStep(stepA).build()
//
//        StudioWizardDialogBuilder(wizard, "Testing").setUseNewUx(true).build().show()
    }
}