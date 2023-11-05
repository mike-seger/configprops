package com.net128.test.apps.configprops

import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

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
