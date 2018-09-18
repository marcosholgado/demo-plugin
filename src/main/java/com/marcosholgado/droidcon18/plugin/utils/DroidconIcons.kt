package com.marcosholgado.droidcon18.plugin.utils

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object DroidconIcons {

    fun load(path: String): Icon = IconLoader.getIcon(path, DroidconIcons::class.java)

    val droidconIcon = load("/icons/template_new_project.png")

}