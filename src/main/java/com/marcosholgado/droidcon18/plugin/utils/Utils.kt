package com.marcosholgado.droidcon18.plugin.utils

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import javax.swing.event.HyperlinkEvent

object Utils {

    fun createNotification(title: String, message: String, project: Project, type: NotificationType, listener: NotificationListener?) {
        val stickyNotification = NotificationGroup("droidcon$title", NotificationDisplayType.BALLOON, true)
        stickyNotification.createNotification(title, message, type, listener).notify(project)
    }

    fun createHyperLink(pre:String, link: String, post: String) = "<html>$pre <a href=\\\"\" + postResult.get() + \"\\\" target=\\\"blank\\\">$link</a> $post</html>"

    fun restartListener() =
        NotificationListener { _, event ->
            if (event.eventType === HyperlinkEvent.EventType.ACTIVATED) {
                ApplicationManager.getApplication().restart()
            }
        }
}
