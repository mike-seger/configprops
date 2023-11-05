package com.net128.test.apps.configprops

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import kotlin.reflect.full.memberProperties

class SpringBeanSerializer(private val defaultSerializer: JsonSerializer<Any>) : StdSerializer<Any>(Any::class.java) {
    //private val springProxySuffix = "EnhancerBySpringCGLIB" //Spring 2.7.x
    override fun serialize(value: Any?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value != null && StringHelper.isSpringBean(value)) {
            serializeSpringBean(value, gen)
        } else {
            defaultSerializer.serialize(value, gen, provider)
        }
    }



    private fun serializeSpringBean(bean: Any, gen: JsonGenerator?) {
        val beanClass = StringHelper.springBeanClass(bean)
        val properties = beanClass.kotlin.memberProperties

        gen?.writeStartObject()
        properties.forEach {
            with(it) {
                gen?.writeObjectField(name, getter.call(bean))
            }
        }
        gen?.writeEndObject()
    }
}

