package com.marcosholgado.demo.plugin.configuration

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import com.marcosholgado.demo.plugin.components.JiraComponent
import com.marcosholgado.demo.plugin.utils.DemoBundle.message
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener



class JiraSettings(private val project: Project): Configurable, DocumentListener {
    private var userField: JTextField? = null
    private var passwordField: JPasswordField? = null
    private var jiraURLField: JTextField? = null
    private var regExField: JTextField? = null
    private var jqlField: JTextField? = null
    private var nameField: JTextField? = null
    private var userLabel: JLabel? = null
    private var passwordLabel: JLabel? = null
    private var jiraURLLabel: JLabel? = null
    private var regExLabel: JTextField? = null
    private var jqlLabel: JTextField? = null
    private var nameLabel: JTextField? = null
    private var mainPanel: JPanel? = null

    private var modified = false

    override fun isModified(): Boolean = modified

    override fun getDisplayName(): String = message("jira.settings.title")

    override fun apply() {
        val config = JiraComponent.getInstance(project)
        config.jiraUrl = jiraURLField!!.text
        config.username = userField!!.text
        config.password = String(passwordField!!.password)
        config.regex = regExField!!.text
        config.jql = jqlField!!.text
        config.name = nameField!!.text

        modified = false
    }

    override fun createComponent(): JComponent? {
        jiraURLField?.document?.addDocumentListener(this)
        userField?.document?.addDocumentListener(this)
        passwordField?.document?.addDocumentListener(this)
        regExField?.document?.addDocumentListener(this)
        jqlField?.document?.addDocumentListener(this)
        nameField?.document?.addDocumentListener(this)


        val config = JiraComponent.getInstance(project)
        userField?.text = config.username
        passwordField?.text = config.password
        jiraURLField?.text = config.jiraUrl
        regExField?.text = config.regex
        jqlField?.text = config.jql
        nameField?.text = config.name

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
