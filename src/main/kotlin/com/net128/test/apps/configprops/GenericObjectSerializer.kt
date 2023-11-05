package com.net128.test.apps.configprops

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.stereotype.Component
import kotlin.reflect.full.memberProperties

@Component
class GenericObjectSerializer : JsonSerializer<Any>() {
    override fun serialize(value: Any?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            gen.writeStartObject()
            value::class.memberProperties.forEach { prop ->
                val propName = prop.name
                val propValue = prop.getter.call(value)
                gen.writeObjectField(propName, propValue)
            }
            gen.writeEndObject()
        }
    }
}
