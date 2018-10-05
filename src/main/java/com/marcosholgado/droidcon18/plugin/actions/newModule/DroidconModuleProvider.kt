package com.marcosholgado.droidcon18.plugin.actions.newModule

import com.android.tools.idea.npw.FormFactor
import com.android.tools.idea.npw.module.ConfigureAndroidModuleStep
import com.android.tools.idea.npw.module.ModuleDescriptionProvider
import com.android.tools.idea.npw.module.ModuleTemplateGalleryEntry
import com.android.tools.idea.npw.model.NewModuleModel
import com.android.tools.idea.npw.model.NewProjectModel
import com.android.tools.idea.templates.TemplateManager
import com.android.tools.idea.wizard.model.SkippableWizardStep
import com.intellij.util.IconUtil
import com.marcosholgado.droidcon18.plugin.utils.DroidconIcons

import java.awt.Image
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
                                metadata.title!!,
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

    private class DroidconModuleEntry internal constructor(private val templateFile: File,
                                                           private val formFactor: FormFactor,
                                                           private val minSdkLevel: Int,
                                                           private val isLibrary: Boolean,
                                                           private val name: String,
                                                           private val description: String
    ) : ModuleTemplateGalleryEntry {

        override fun getTemplateFile(): File = templateFile

        override fun getFormFactor(): FormFactor = formFactor

        override fun isLibrary(): Boolean = isLibrary

        override fun isInstantApp(): Boolean = false

        override fun getIcon(): Image? = IconUtil.toImage(DroidconIcons.droidconIcon)

        override fun getName(): String = name

        override fun getDescription(): String? = description

        override fun toString(): String = name

        override fun createStep(model: NewModuleModel): SkippableWizardStep<*> {
            val basePackage = NewProjectModel.getSuggestedProjectPackage(model.project.value, false)
            return ConfigureAndroidModuleStep(model, formFactor, minSdkLevel, basePackage, isLibrary, false, name)
        }

    }
}