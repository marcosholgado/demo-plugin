package com.marcosholgado.droidcon18.plugin.components

import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.marcosholgado.droidcon18.plugin.utils.DroidconBundle
import com.marcosholgado.droidcon18.plugin.utils.FileUtils
import com.marcosholgado.droidcon18.plugin.utils.Utils
import java.io.Serializable
import javax.swing.event.HyperlinkEvent

@State(name = "DroidconConfiguration", storages = [
    Storage(value = "droidconConfiguration.xml")
])
class DroidconComponent: ApplicationComponent, Serializable, PersistentStateComponent<DroidconComponent> {

    private var templatesRevision = 1
    private var templatesLocalRevision = 0 // Do not change

    override fun getState(): DroidconComponent? = this

    override fun loadState(state: DroidconComponent) = XmlSerializerUtil.copyBean(state, this)

    private fun shouldUpdateTemplates() = templatesLocalRevision < templatesRevision

    fun updateTemplatesRevision() {
        templatesLocalRevision = templatesRevision
    }

    override fun initComponent() {
        super.initComponent()

        if (shouldUpdateTemplates()) {
            val message = Utils.createHyperLink(
                    DroidconBundle.message("component.jira.template.success.pre"),
                    DroidconBundle.message("component.jira.template.success.link"),
                    DroidconBundle.message("component.jira.template.success.post")
            )
            val listener = NotificationListener { notification, event ->
                if (event.eventType === HyperlinkEvent.EventType.ACTIVATED) {
                    notification.hideBalloon()
                    val sourceDirectoryList = listOf("/androidTemplates/", "/projectTemplates/")
                    val writeDirectoryList = listOf("/.android/templates/other", "/.android/templates")
                    FileUtils.copyTemplates(sourceDirectoryList, writeDirectoryList, null)
                }
            }
            Utils.createNotification(DroidconBundle.message("dialog.update"), message, null, NotificationType.INFORMATION, listener)
        }
    }

}