package com.marcosholgado.demo.plugin.utils

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.marcosholgado.demo.plugin.utils.DemoBundle.message
import javax.swing.event.HyperlinkEvent

object Utils {

    fun createNotification(title: String, message: String, project: Project?, type: NotificationType, listener: NotificationListener?) {
        val stickyNotification = NotificationGroup("demo$title", NotificationDisplayType.BALLOON, true)
        stickyNotification.createNotification(title, message, type, listener).notify(project)
    }

    fun createHyperLink(pre:String, link: String, post: String) = message("utils.hyperlink.code", pre, link, post)

    fun restartListener() =
        NotificationListener { _, event ->
            if (event.eventType === HyperlinkEvent.EventType.ACTIVATED) {
                ApplicationManager.getApplication().restart()
            }
        }
}
