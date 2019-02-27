package com.marcosholgado.demo.plugin.liveTemplates

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider

class DemoLiveTemplateProvider: DefaultLiveTemplatesProvider {

    override fun getDefaultLiveTemplateFiles(): Array<String> = arrayOf("liveTemplates/Demo")

    override fun getHiddenLiveTemplateFiles(): Array<String>? = null

}