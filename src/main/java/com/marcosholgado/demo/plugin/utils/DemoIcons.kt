package com.marcosholgado.demo.plugin.utils

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object DemoIcons {

    private fun load(path: String): Icon = IconLoader.getIcon(path, DemoIcons::class.java)

    val demoIcon = load("/icons/template_new_project.png")

}