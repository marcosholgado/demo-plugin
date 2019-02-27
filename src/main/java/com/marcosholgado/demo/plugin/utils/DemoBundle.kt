package com.marcosholgado.demo.plugin.utils

import com.intellij.CommonBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import java.lang.ref.Reference
import java.lang.ref.SoftReference
import java.util.*

object DemoBundle {
    @NonNls
    private val BUNDLE_NAME = "messages.DemoBundle"
    private var ourBundle: Reference<ResourceBundle>? = null

    private fun getBundle(): ResourceBundle {
        var bundle = com.intellij.reference.SoftReference.dereference(ourBundle)
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(BUNDLE_NAME)
            ourBundle = SoftReference(bundle)
        }
        return bundle!!
    }

    fun message(@PropertyKey(resourceBundle = "messages.DemoBundle") key: String, vararg params: Any): String {
        return CommonBundle.message(getBundle(), key, *params)
    }

}