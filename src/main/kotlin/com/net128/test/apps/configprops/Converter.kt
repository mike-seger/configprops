package com.net128.test.apps.configprops

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue

object Converter {
    inline fun <reified T : Any> convertToCore(source: T): Any {
        return ObjectMapper().convertValue(source, T::class.java.superclass)
        /*val coreType = T::class.primaryConstructor!!.parameters.first().type.java
        val coreInstance = coreType.newInstance()

        val sourceProperties = T::class.declaredMemberProperties
        val coreProperties = coreType.declaredFields

        for (coreProperty in coreProperties) {
            val propertyName = coreProperty.name
            val sourceProperty = sourceProperties.firstOrNull { it.name == propertyName }

            if (sourceProperty != null) {
                coreProperty.isAccessible = true
                val value = sourceProperty.get(source)
                coreProperty.set(coreInstance, value)
            }
        }

        return coreInstance*/
    }
}