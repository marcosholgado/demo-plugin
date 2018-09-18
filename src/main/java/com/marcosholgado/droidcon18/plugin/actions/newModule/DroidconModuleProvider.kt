package com.marcosholgado.droidcon18.plugin.actions.newModule

import com.android.tools.idea.npw.FormFactor
import com.android.tools.idea.npw.module.ConfigureAndroidModuleStep
import com.android.tools.idea.npw.module.ModuleDescriptionProvider
import com.android.tools.idea.npw.module.ModuleTemplateGalleryEntry
import com.android.tools.idea.npw.module.NewModuleModel
import com.android.tools.idea.templates.TemplateManager
import com.android.tools.idea.wizard.model.SkippableWizardStep
import com.marcosholgado.droidcon18.plugin.utils.DroidconBundle.message
import com.marcosholgado.droidcon18.plugin.utils.DroidconIcons

import javax.swing.*
import java.io.File
import java.util.ArrayList

class DroidconModuleProvider : ModuleDescriptionProvider {
    override fun getDescriptions(): Collection<ModuleTemplateGalleryEntry> {
        val res = ArrayList<ModuleTemplateGalleryEntry>()

        val manager = TemplateManager.getInstance()
        val templateDirectories = TemplateManager.getExtraTemplateRootFolders()

        for (dir in templateDirectories) {
            if (dir.parent.endsWith(".android")) {
                val applicationTemplates = TemplateManager.getTemplatesFromDirectory(dir, true)
                for (templateFile in applicationTemplates) {
                    val metadata = manager.getTemplateMetadata(templateFile)
                    if (metadata == null || metadata.formFactor == null || metadata.category != "Droidcon") continue

                    val minSdk = metadata.minSdk
                    val formFactor = FormFactor.get(metadata.formFactor!!)
                    if (formFactor == FormFactor.MOBILE) {
                        res.add(
                                DroidconModuleEntry(templateFile,
                                formFactor,
                                minSdk,
                                true,
                                DroidconIcons.droidconIcon,
                                message("droidcon.wizard.new.module"),
                                metadata.description!!)
                        )
                    } else {
                        // Hide other types
                    }
                }
            }
        }
        return res
    }

    private class DroidconModuleEntry internal constructor(private val myTemplateFile: File, private val myFormFactor: FormFactor, private val myMinSdkLevel: Int, private val myIsLibrary: Boolean,
                                                           private val myIcon: Icon, private val myName: String, private val myDescription: String) : ModuleTemplateGalleryEntry {

        override fun getTemplateFile(): File = myTemplateFile

        override fun getFormFactor(): FormFactor = myFormFactor

        override fun isLibrary(): Boolean = myIsLibrary

        override fun isInstantApp(): Boolean = false

        override fun getIcon(): Icon? = myIcon

        override fun getName(): String = myName

        override fun getDescription(): String? = myDescription

        override fun toString(): String = name

        override fun createStep(model: NewModuleModel): SkippableWizardStep<*> =
                ConfigureAndroidModuleStep(model, myFormFactor, myMinSdkLevel, isLibrary, false, myName)

    }
}