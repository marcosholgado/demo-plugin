package com.marcosholgado.demo.plugin.components

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import java.io.Serializable

@State(name = "JiraConfiguration", storages = [
Storage(value = "jiraConfiguration.xml")
])
class JiraComponent constructor(project: Project? = null): AbstractProjectComponent(project), Serializable, PersistentStateComponent<JiraComponent> {

    var jiraUrl: String = ""
    var username: String = ""
    var password: String = ""
    var regex: String = ""
    var jql: String = ""
    var name: String = ""

    override fun getState(): JiraComponent? = this

    override fun loadState(state: JiraComponent) = XmlSerializerUtil.copyBean(state, this)

    companion object {
        fun getInstance(project: Project): JiraComponent = project.getComponent(JiraComponent::class.java)
    }
}