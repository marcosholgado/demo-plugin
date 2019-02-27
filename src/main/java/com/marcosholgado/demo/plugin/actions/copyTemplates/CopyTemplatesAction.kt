package com.marcosholgado.demo.plugin.actions.copyTemplates

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.marcosholgado.demo.plugin.utils.FileUtils

class CopyTemplatesAction: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val sourceDirectoryList = listOf("/androidTemplates/", "/projectTemplates/")
        val writeDirectoryList = listOf("/.android/templates/other", "/.android/templates")
        FileUtils.copyTemplates(sourceDirectoryList, writeDirectoryList, event.project!!)
    }
}