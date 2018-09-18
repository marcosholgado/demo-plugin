package com.marcosholgado.droidcon18.plugin.utils

import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.marcosholgado.droidcon18.plugin.components.DroidconComponent
import com.marcosholgado.droidcon18.plugin.utils.DroidconBundle.message
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.JarURLConnection
import java.nio.file.Files

object FileUtils {

    fun copyTemplates(sourceDirectory: String, writeDirectory: String, project: Project) {
        val dirURL = javaClass.getResource(sourceDirectory)
        val path = sourceDirectory.substring(1)
        val home = System.getProperty("user.home")

        File(home + writeDirectory).mkdirs()

        if (dirURL != null && dirURL.protocol == "jar") {
            val jarConnection = dirURL.openConnection() as JarURLConnection
            val jar = jarConnection.jarFile
            val entries = jar.entries()

            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                val name = entry.name
                if (!name.startsWith(path)) {
                    continue
                }
                val entryTail = name.substring(path.length)

                val f = File(home + writeDirectory + File.separator + entryTail)
                if (entry.isDirectory) {
                    try {
                        Files.createDirectory(f.toPath())
                    } catch (e: Exception) {
                        println(e.message)
                    }
                } else {
                    val inputStream = jar.getInputStream(entry)
                    val os = BufferedOutputStream(FileOutputStream(f))
                    val buffer = ByteArray(4096)
                    var readCount: Int
                    readCount = inputStream.read(buffer)
                    while (readCount > 0) {
                        os.write(buffer, 0, readCount)
                        readCount = inputStream.read(buffer)
                    }
                    os.close()
                    inputStream.close()
                }
            }
            val component = ApplicationManager.getApplication().getComponent(DroidconComponent::class.java)
            component.updateTemplatesRevision()

            val msg = Utils.createHyperLink(
                    message("utils.template.success.pre"),
                    message("utils.template.success.link"),
                    message("utils.template.success.post")
            )

            Utils.createNotification(
                    message("utils.template.completed"),
                    msg,
                    project,
                    NotificationType.INFORMATION,
                    Utils.restartListener()
            )
        } else if (dirURL == null) {
            Utils.createNotification(
                    message("utils.template.error"),
                    message("utils.template.error.classpath", sourceDirectory),
                    project,
                    NotificationType.ERROR,
                    null
            )
        } else {
            Utils.createNotification(
                    message("utils.template.error"),
                    message("utils.template.error.extracting", dirURL),
                    project,
                    NotificationType.ERROR,
                    null
            )
        }
    }
}