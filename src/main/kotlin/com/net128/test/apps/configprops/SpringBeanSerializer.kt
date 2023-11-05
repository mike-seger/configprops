package com.net128.test.apps.configprops

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import kotlin.reflect.full.memberProperties

class SpringBeanSerializer(private val defaultSerializer: JsonSerializer<Any>) : StdSerializer<Any>(Any::class.java) {
    override fun serialize(value: Any?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value != null && SpringHelper.isSpringBean(value)) {
            serializeSpringBean(value, gen)
        } else {
            defaultSerializer.serialize(value, gen, provider)
        }
    }

    private fun serializeSpringBean(bean: Any, gen: JsonGenerator?) {
        val beanClass = SpringHelper.springBeanClass(bean)
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

object SpringHelper {
    fun isSpringBean(bean: Any): Boolean {
        return bean.javaClass.name.matches((".*[\$][\$].*$springProxySuffix.*").toRegex())
    }

    fun springBeanClass(bean: Any) : Class<*> {
        return Class.forName(bean.javaClass.name.replace(("[\$][\$]$springProxySuffix.*").toRegex(), ""))
    }

    private const val springProxySuffix = "SpringCGLIB"
}

@Configuration
class JacksonConfiguration {
    @Bean
    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        val objectMapper = builder.build<ObjectMapper>()

        val springBeanModule = SimpleModule()
        springBeanModule.setSerializerModifier(object : BeanSerializerModifier() {
            override fun modifySerializer(config: SerializationConfig, beanDesc: BeanDescription, serializer: JsonSerializer<*>): JsonSerializer<*> {
                @Suppress("UNCHECKED_CAST")
                return SpringBeanSerializer(serializer as JsonSerializer<Any>)
            }
        })

        objectMapper.registerModule(springBeanModule)

        return objectMapper
    }
}
