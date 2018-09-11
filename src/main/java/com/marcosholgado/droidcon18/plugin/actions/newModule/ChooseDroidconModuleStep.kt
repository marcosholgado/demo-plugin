package com.marcosholgado.droidcon18.plugin.actions.newModule

import com.android.tools.adtui.util.FormScalingUtil
import com.android.tools.adtui.ASGallery
import com.android.tools.idea.npw.module.ModuleGalleryEntry
import com.android.tools.idea.npw.module.ModuleTemplateGalleryEntry
import com.android.tools.idea.npw.module.NewModuleModel
import com.android.tools.idea.wizard.model.ModelWizard
import com.android.tools.idea.wizard.model.ModelWizardStep
import com.android.tools.idea.wizard.model.SkippableWizardStep
import com.google.common.collect.Lists
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.IconUtil

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.util.*

import com.android.tools.idea.wizard.WizardConstants.DEFAULT_GALLERY_THUMBNAIL_SIZE
import org.jetbrains.android.util.AndroidBundle.message

/**
 * This step allows the user to select which type of module they want to create.
 */
class ChooseDroidconModuleStep(model: NewModuleModel, moduleGalleryEntries: List<ModuleGalleryEntry>) : ModelWizardStep<NewModuleModel>(model, message("android.wizard.module.new.module.header")) {
    private val myModuleGalleryEntryList: List<ModuleGalleryEntry> = moduleGalleryEntries
    private val myRootPanel: JComponent

    private var myFormFactorGallery: ASGallery<ModuleGalleryEntry>? = null
    private var myModuleDescriptionToStepMap: MutableMap<ModuleGalleryEntry, SkippableWizardStep<*>>? = null

    init {
        myRootPanel = createGallery()
        FormScalingUtil.scaleComponentTree(this.javaClass, myRootPanel)
    }

    override fun getComponent(): JComponent = myRootPanel


    public override fun createDependentSteps(): Collection<ModelWizardStep<*>> {
        val allSteps = Lists.newArrayList<ModelWizardStep<*>>()
        myModuleDescriptionToStepMap = HashMap()
        for (moduleGalleryEntry in myModuleGalleryEntryList) {
            val step = moduleGalleryEntry.createStep(model)
            allSteps.add(step)
            myModuleDescriptionToStepMap!![moduleGalleryEntry] = step
        }

        return allSteps
    }

    private fun createGallery(): JComponent {
        myFormFactorGallery = object : ASGallery<ModuleGalleryEntry>(
                JBList.createDefaultListModel<Any>(),
                { image -> if (image!!.icon == null) null else IconUtil.toImage(image.icon!!) },
                { label ->
                    label?.name ?: message("android.wizard.gallery.item.none")
                }, DEFAULT_GALLERY_THUMBNAIL_SIZE, null
        ) {

            override fun getPreferredScrollableViewportSize(): Dimension {
                // The default implementations assigns a height as tall as the screen.
                // When calling setVisibleRowCount(2), the underlying implementation is buggy, and  will have a gap on the right and when the user
                // resizes, it enters on an adjustment loop at some widths (can't decide to fit 3 or for elements, and loops between the two)
                val cellSize = computeCellSize()
                val heightInsets = insets.top + insets.bottom
                val widthInsets = insets.left + insets.right
                // Don't want to show an exact number of rows, since then it's not obvious there's another row available.
                return Dimension(cellSize.width * 5 + widthInsets, (cellSize.height * 2.2).toInt() + heightInsets)
            }
        }

        myFormFactorGallery!!.border = BorderFactory.createLineBorder(JBColor.border())
        val accessibleContext = myFormFactorGallery!!.accessibleContext
        if (accessibleContext != null) {
            accessibleContext.accessibleDescription = title
        }
        return JBScrollPane(myFormFactorGallery)
    }

    override fun onWizardStarting(wizard: ModelWizard.Facade) {
        myFormFactorGallery!!.model = JBList.createDefaultListModel<Any>(*myModuleGalleryEntryList.toTypedArray())
        myFormFactorGallery!!.setDefaultAction(object : AbstractAction() {
            override fun actionPerformed(actionEvent: ActionEvent) {
                wizard.goForward()
            }
        })

        myFormFactorGallery!!.selectedIndex = 0
    }

    override fun onProceeding() {
        model.templateValues.clear()

        // This wizard includes a step for each module, but we only visit the selected one. First, we hide all steps (in case we visited a
        // different module before and hit back), and then we activate the step we care about.
        val selectedEntry = myFormFactorGallery!!.selectedElement
        myModuleDescriptionToStepMap!!.forEach { galleryEntry, step -> step.setShouldShow(galleryEntry === selectedEntry) }

        val templateEntry = selectedEntry as? ModuleTemplateGalleryEntry

        model.isLibrary.set(templateEntry?.isLibrary ?: false)
        model.instantApp().set(templateEntry?.isInstantApp ?: false)
        model.templateFile().setNullableValue(templateEntry?.templateFile)
    }

    override fun getPreferredFocusComponent(): JComponent? {
        return myFormFactorGallery
    }
}
