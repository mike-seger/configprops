package com.net128.test.apps.configprops

import com.fasterxml.jackson.core.JsonGenerator
import kotlin.reflect.KClass


import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import kotlin.reflect.full.superclasses

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class SuperclassForSerialization(val superclass: KClass<*>)

class GenericSerializer : StdSerializer<Any>(Any::class.java) {
    private var objectMapper = ObjectMapper()

    constructor() : this(null)
    constructor(t: Class<Any>?) : super(t)

//    init {
//        val module = SimpleModule()
//        module.addSerializer(this)
//        objectMapper.registerModule(module)
//    }
    override fun serialize(value: Any?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (value != null && gen != null && provider != null && isSpringBean(value::class)) {
            val superClass = value::class.superclasses.firstOrNull()
           // val objectMapper = provider.objectMapper

            if (superClass != null) {
                val infoCore = convertToCore(value, objectMapper, superClass)
                objectMapper.writeValue(gen, infoCore)
            } else {
                // Default serialization behavior
                provider.defaultSerializeValue(value, gen)
            }
        }
    }

    private fun isSpringBean(clazz: KClass<*>): Boolean {
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(Annotation::class.java))
        return scanner.findCandidateComponents(clazz.qualifiedName+"")
                .map { it.beanClassName }
                .contains(clazz.java.name)
    }

    private fun convertToCore(source: Any, objectMapper: ObjectMapper, superClass: KClass<*>): Any {
        val superClassType = superClass.java
        return objectMapper.convertValue(source, superClassType)
    }
}