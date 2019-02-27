package com.marcosholgado.demo.plugin.utils

import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.marcosholgado.demo.plugin.components.DemoComponent
import com.marcosholgado.demo.plugin.utils.DemoBundle.message
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.JarURLConnection
import java.nio.file.Files

object FileUtils {

    fun copyTemplates(sourceDirectoryList: List<String>, writeDirectoryList: List<String>, project: Project?) {

        sourceDirectoryList.forEachIndexed { index, sourceDirectory ->

            val writeDirectory = writeDirectoryList[index]
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
            } else if (dirURL == null) {
                Utils.createNotification(
                        message("utils.template.error"),
                        message("utils.template.error.classpath", sourceDirectory),
                        project,
                        NotificationType.ERROR,
                        null
                )
                return
            } else {
                Utils.createNotification(
                        message("utils.template.error"),
                        message("utils.template.error.extracting", dirURL),
                        project,
                        NotificationType.ERROR,
                        null
                )
                return
            }
        }
        val component = ApplicationManager.getApplication().getComponent(DemoComponent::class.java)
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
    }
}