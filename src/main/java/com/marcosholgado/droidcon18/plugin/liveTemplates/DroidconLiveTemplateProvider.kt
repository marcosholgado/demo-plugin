package com.marcosholgado.droidcon18.plugin.liveTemplates

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider

class DroidconLiveTemplateProvider: DefaultLiveTemplatesProvider {

    override fun getDefaultLiveTemplateFiles(): Array<String> = arrayOf("liveTemplates/Droidcon")

    override fun getHiddenLiveTemplateFiles(): Array<String>? = null

}