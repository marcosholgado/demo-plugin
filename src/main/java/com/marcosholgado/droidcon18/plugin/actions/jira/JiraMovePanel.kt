package com.marcosholgado.droidcon18.plugin.actions.jira

import com.intellij.openapi.ui.ComboBox
import com.marcosholgado.droidcon18.plugin.actions.jira.network.Transition
import java.awt.Dimension
import javax.swing.*

class JiraMovePanel : JPanel() {

    private val txtJiraTicket = JTextField()
    private val comboTransitions = ComboBox<Transition>()
    private val txtComment = JTextArea()

    init {
        initComponents()
    }

    private fun initComponents() {
        layout = null

        val lblJiraTicket = JLabel("Jira Ticket")
        lblJiraTicket.setBounds(25, 33, 77, 16)
        add(lblJiraTicket)

        txtJiraTicket.setBounds(114, 28, 183, 26)
        add(txtJiraTicket)
        txtJiraTicket.columns = 10

        val lblTransition = JLabel("Transition")
        lblTransition.setBounds(25, 75, 77, 16)
        add(lblTransition)

        comboTransitions.setBounds(114, 71, 183, 27)
        add(comboTransitions)

        val lblComment = JLabel("Comment")
        lblComment.setBounds(25, 119, 77, 16)
        add(lblComment)

        txtComment.setBounds(124, 110, 345, 255)
        add(txtComment)

    }

    override fun getPreferredSize(): Dimension = Dimension(500, 305)

    fun addTransition(transition: Transition) = comboTransitions.addItem(transition)

    fun setBranch(branch: String) {
        txtJiraTicket.text = branch
    }

    fun getTransition() : Transition = comboTransitions.selectedItem as Transition
}