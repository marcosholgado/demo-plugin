package com.marcosholgado.demo.plugin.components

import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.marcosholgado.demo.plugin.utils.DemoBundle
import com.marcosholgado.demo.plugin.utils.FileUtils
import com.marcosholgado.demo.plugin.utils.Utils
import java.io.Serializable
import javax.swing.event.HyperlinkEvent

@State(name = "DemoConfiguration", storages = [
    Storage(value = "demoConfiguration.xml")
])
class DemoComponent: ApplicationComponent, Serializable, PersistentStateComponent<DemoComponent> {

    var templatesRevision = 1
    var templatesLocalRevision = 0 // Do not change

    override fun getState(): DemoComponent? = this

    override fun loadState(state: DemoComponent) = XmlSerializerUtil.copyBean(state, this)

    private fun shouldUpdateTemplates() = templatesLocalRevision < templatesRevision

    fun updateTemplatesRevision() {
        templatesLocalRevision = templatesRevision
    }

    override fun initComponent() {
        super.initComponent()

        if (shouldUpdateTemplates()) {
            val message = Utils.createHyperLink(
                    DemoBundle.message("component.jira.template.success.pre"),
                    DemoBundle.message("component.jira.template.success.link"),
                    DemoBundle.message("component.jira.template.success.post")
            )
            val listener = NotificationListener { notification, event ->
                if (event.eventType === HyperlinkEvent.EventType.ACTIVATED) {
                    notification.hideBalloon()
                    val sourceDirectoryList = listOf("/androidTemplates/", "/projectTemplates/")
                    val writeDirectoryList = listOf("/.android/templates/other", "/.android/templates")
                    FileUtils.copyTemplates(sourceDirectoryList, writeDirectoryList, null)
                }
            }
            Utils.createNotification(DemoBundle.message("dialog.update"), message, null, NotificationType.INFORMATION, listener)
        }
    }

}