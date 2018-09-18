package com.marcosholgado.droidcon18.plugin.actions.jiraMove

import com.intellij.openapi.ui.ComboBox
import com.marcosholgado.droidcon18.plugin.actions.jiraMove.network.Transition
import com.marcosholgado.droidcon18.plugin.utils.DroidconBundle.message
import java.awt.Dimension
import javax.swing.*

class JiraMovePanel : JPanel() {

    private val comboTransitions = ComboBox<Transition>()
    val txtJiraTicket = JTextField()
    val txtComment = JTextArea()

    init {
        initComponents()
    }

    private fun initComponents() {
        layout = null

        val lblJiraTicket = JLabel(message("jira.move.label.ticket"))
        lblJiraTicket.setBounds(25, 33, 77, 16)
        add(lblJiraTicket)

        txtJiraTicket.setBounds(114, 28, 183, 26)
        add(txtJiraTicket)
        txtJiraTicket.columns = 10

        val lblTransition = JLabel(message("jira.move.label.transition"))
        lblTransition.setBounds(25, 75, 77, 16)
        add(lblTransition)

        comboTransitions.setBounds(114, 71, 183, 27)
        add(comboTransitions)

        val lblComment = JLabel(message("jira.move.label.comment"))
        lblComment.setBounds(25, 119, 77, 16)
        add(lblComment)

        txtComment.setBounds(124, 110, 345, 255)
        add(txtComment)

    }

    override fun getPreferredSize(): Dimension = Dimension(500, 305)

    fun addTransition(transition: Transition) = comboTransitions.addItem(transition)

    fun setTicket(ticket: String) {
        txtJiraTicket.text = ticket
    }

    fun getTransition() : Transition = comboTransitions.selectedItem as Transition
}