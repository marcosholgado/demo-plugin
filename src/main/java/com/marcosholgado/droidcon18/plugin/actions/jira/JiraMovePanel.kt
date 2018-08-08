package com.marcosholgado.droidcon18.plugin.actions.jira

import com.marcosholgado.droidcon18.plugin.actions.jira.network.Transition
import javax.swing.*

class JiraMovePanel {
    var titleLabel: JLabel? = null
    var branchLabel: JLabel? = null
    var branchField: JTextField? = null
    var transitionCombo: JComboBox<Transition>? = null
    var commentLabel: JLabel? = null
    var textArea1: JTextArea? = null
    var cancelButton: JButton? = null
    var okButton: JButton? = null
    var mainPanel: JPanel? = null

}