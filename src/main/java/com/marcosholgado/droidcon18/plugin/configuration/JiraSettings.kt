package com.marcosholgado.droidcon18.plugin.configuration

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.marcosholgado.droidcon18.plugin.components.JiraComponent
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener



class JiraSettings(private val project: Project): Configurable, DocumentListener {
    private var userField: JTextField? = null
    private var passwordField: JPasswordField? = null
    private var jiraURLField: JTextField? = null
    private var userLabel: JLabel? = null
    private var passwordLabel: JLabel? = null
    private var jiraURLLabel: JLabel? = null
    private var mainPanel: JPanel? = null

    private var modified = false

    override fun isModified(): Boolean = modified

    override fun getDisplayName(): String = "Jira Plugin"

    override fun apply() {
        val config = JiraComponent.getInstance(project)
        config.jiraUrl = jiraURLField!!.text
        config.username = userField!!.text
        config.password = String(passwordField!!.password)

        modified = false
    }

    override fun createComponent(): JComponent? {
        jiraURLField?.document?.addDocumentListener(this)
        userField?.document?.addDocumentListener(this)
        passwordField?.document?.addDocumentListener(this)


        val config = JiraComponent.getInstance(project)
        userField?.text = config.username
        passwordField?.text = config.password
        jiraURLField?.text = config.jiraUrl

        return mainPanel
    }

    override fun changedUpdate(e: DocumentEvent?) {
        modified = true
    }

    override fun insertUpdate(e: DocumentEvent?) {
        modified = true
    }

    override fun removeUpdate(e: DocumentEvent?) {
        modified = true
    }
}
