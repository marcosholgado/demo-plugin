package com.marcosholgado.droidcon18.plugin.components

import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import java.io.Serializable

@State(name = "DroidconConfiguration", storages = [
    Storage(value = "droidconConfiguration.xml")
])
class DroidconComponent: ApplicationComponent, Serializable, PersistentStateComponent<DroidconComponent> {

    var templatesRevision = 1
    var templatesLocalRevision = 0 // Do not change

    override fun getState(): DroidconComponent? = this

    override fun loadState(state: DroidconComponent) = XmlSerializerUtil.copyBean(state, this)

    fun shouldUpdateTemplates() = templatesLocalRevision < templatesRevision

    fun updateTemplatesRevision() {
        templatesLocalRevision = templatesRevision
    }
}