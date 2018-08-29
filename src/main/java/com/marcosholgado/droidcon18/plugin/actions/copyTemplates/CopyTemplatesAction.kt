package com.marcosholgado.droidcon18.plugin.actions.copyTemplates

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.marcosholgado.droidcon18.plugin.utils.FileUtils

class CopyTemplatesAction: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        FileUtils.extract("/androidTemplates/", "/.android/templates/other", event.project!!)
    }
}