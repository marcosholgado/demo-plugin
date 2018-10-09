package com.marcosholgado.droidcon18.plugin.actions.jiraNewTicket

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.containers.isNullOrEmpty
import com.marcosholgado.droidcon18.plugin.actions.jiraMove.network.Issue
import java.awt.Dimension
import javax.swing.*
import javax.swing.JTextPane

class JiraNewTicketPanel : JPanel() {

    private val comboIssues = ComboBox<Issue>()
    private val descriptionText = JTextPane()

    init {
        initComponents()
    }

    private fun initComponents() {
        layout = null

        val lblTicket = JLabel("Ticket")
        lblTicket.setBounds(28, 30, 77, 16)
        add(lblTicket)

        comboIssues.setBounds(117, 26, 352, 27)
        add(comboIssues)

        val lblDescription = JLabel("Description")
        lblDescription.setBounds(28, 58, 77, 16)
        add(lblDescription)

        descriptionText.setBounds(45, 101, 438, 262)
        descriptionText.contentType = "text/html"
        descriptionText.isEditable = false

        val scrollPane = JBScrollPane(descriptionText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
        scrollPane.setBounds(21, 84, 477, 302)
        add(scrollPane)

        comboIssues.addActionListener {
            descriptionText.text = ""
            val builder = StringBuilder()

            val item = comboIssues.selectedItem as Issue
            val descriptionList = item.fields.description
            descriptionList?.let {
                builder.append("<html>")
                for(description in it.content) {
                    for(content in description.content) {
                        if (content.marks.isNullOrEmpty()) {
                            builder.append(content.text)
                        } else {
                            for (mark in content.marks!!) {
                                builder.append("<${mark.type}>")
                                builder.append(content.text)
                                builder.append("</${mark.type}>")
                            }
                        }
                        builder.append("</br>")
                    }
                }
                builder.append("</html>")
                descriptionText.text = builder.toString()
            }
        }
    }

    override fun getPreferredSize(): Dimension = Dimension(500, 395)

    fun addIssue(issue: Issue) = comboIssues.addItem(issue)

    fun getIssue(): Issue = comboIssues.selectedItem as Issue
}