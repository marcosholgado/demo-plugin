package com.marcosholgado.droidcon18.plugin.components

import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import java.io.Serializable
import com.marcosholgado.droidcon18.plugin.utils.FileUtils
import com.marcosholgado.droidcon18.plugin.utils.Utils
import javax.swing.event.HyperlinkEvent


@State(name = "JiraConfiguration", storages = [
Storage(value = "jiraConfiguration.xml")
])
class JiraComponent constructor(project: Project? = null): AbstractProjectComponent(project), Serializable, PersistentStateComponent<JiraComponent> {

    var jiraUrl: String = ""
    var username: String = ""
    var password: String = ""
    var regex: String = ""

    override fun getState(): JiraComponent? = this

    override fun loadState(state: JiraComponent) = XmlSerializerUtil.copyBean(state, this)

    override fun initComponent() {
        super.initComponent()

        val component = ApplicationManager.getApplication().getComponent(DroidconComponent::class.java)

        if (component.shouldUpdateTemplates()) {
            val message = Utils.createHyperLink("Android templates have been updated, click", "here", "to install the new templates")
            val listener = NotificationListener { notification, event ->
                if (event.eventType === HyperlinkEvent.EventType.ACTIVATED) {
                    notification.hideBalloon()
                    FileUtils.copyTemplates("/androidTemplates/", "/.android/templates/other", this.myProject)
                }
            }
            Utils.createNotification("Update", message, myProject, NotificationType.INFORMATION, listener)
        }
    }

    companion object {
        fun getInstance(project: Project): JiraComponent = project.getComponent(JiraComponent::class.java)
    }
}