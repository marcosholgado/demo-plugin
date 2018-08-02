package com.marcosholgado.droidcon18.plugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import git4idea.repo.GitRepositoryManager

class TestAction: AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project

        project?.let {
            val repositoryManager = GitRepositoryManager.getInstance(project)
            var message = ""
            for (repository in repositoryManager.repositories) {
                message += " Branch is ${repository.currentBranch?.name}"
            }

            Messages.showMessageDialog(project, message, "My title", Messages.getInformationIcon())
        }
    }
}