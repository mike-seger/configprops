package com.net128.test.apps.configprops

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.util.Date

class CustomDateSerializer : JsonSerializer<Date>() {
    override fun serialize(date: Date?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (date != null && gen != null) {
            // Serialize the Date without the problematic "timestamp" field
            gen.writeString(date.toString())
        }
    }
}
