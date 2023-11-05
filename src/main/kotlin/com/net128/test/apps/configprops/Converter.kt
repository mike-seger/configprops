package com.net128.test.apps.configprops

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

object Converter {
    inline fun <reified T : Any, reified R : Any> convert(source: T): R {
        val coreInstance = R::class.createInstance()
        val sourceProperties = T::class.memberProperties
        val coreProperties = R::class.java.declaredFields

        for (coreProperty in coreProperties) {
            val propertyName = coreProperty.name
            val sourceProperty = sourceProperties.firstOrNull { it.name == propertyName }

            if (sourceProperty != null) {
                coreProperty.isAccessible = true
                val value = sourceProperty.get(source)
                coreProperty.set(coreInstance, value)
            }
        }

        return coreInstance
    }
}