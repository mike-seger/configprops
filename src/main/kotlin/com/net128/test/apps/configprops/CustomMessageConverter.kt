package com.net128.test.apps.configprops

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.server.ServerHttpResponse
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class CustomMessageConverter(
    private val objectMapper: ObjectMapper
) : HttpMessageConverter<Any> {

    init {
        val module = SimpleModule()
        module.addSerializer(GenericSerializer())
        objectMapper.registerModule(module)
    }

    override fun canRead(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return false // This converter does not support reading
    }

    override fun canWrite(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return true
    }

    override fun getSupportedMediaTypes(): List<MediaType> {
        return listOf(MediaType.APPLICATION_JSON)
    }

    @Throws(HttpMessageNotReadableException::class, IOException::class)
    override fun read(
        clazz: Class<out Any>,
        inputMessage: HttpInputMessage
    ): Any {
        throw HttpMessageNotReadableException("This converter does not support reading.")
    }

    @Throws(HttpMessageNotWritableException::class, IOException::class)
    override fun write(
        obj: Any,
        contentType: MediaType?,
        outputMessage: HttpOutputMessage
    ) {
        val response = outputMessage as ServerHttpResponse
        val body = objectMapper.writeValueAsBytes(obj)
        response.headers.contentType = MediaType.APPLICATION_JSON
        response.body.write(body)
    }
}

@Component
class JacksonConfiguration
{
    @Bean
    fun customMessageConverterBean(objectMapper: ObjectMapper): HttpMessageConverter<Any> {
        return CustomMessageConverter(objectMapper)
    }

    @Bean
    fun jacksonMessageConverterBean(objectMapper: ObjectMapper): MappingJackson2HttpMessageConverter {
        val converter = MappingJackson2HttpMessageConverter()
        converter.objectMapper = objectMapper
        return converter
    }
}
