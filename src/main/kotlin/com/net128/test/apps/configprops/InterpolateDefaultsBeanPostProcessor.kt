package com.net128.test.apps.configprops

import com.net128.test.apps.configprops.StringHelper.isSpringBeanProperty
import com.net128.test.apps.configprops.StringHelper.springBeanClass
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

//@Component
class InterpolateDefaultsBeanPostProcessor : BeanPostProcessor, ApplicationContextAware {
    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        val annotation = AnnotationUtils.findAnnotation(bean.javaClass, InterpolateDefaults::class.java)
        if (annotation != null) {
            interpolateDefaults(bean)
        }
        return bean
    }

    private fun interpolateDefaults(bean: Any) {
        val properties = springBeanClass(bean).kotlin.declaredMemberProperties
        for (property in properties) {
            property.isAccessible = true
            if(isSpringBeanProperty(property.name)) continue
            if (property is KMutableProperty<*>) {
                val defaultValue = property.getter.call(bean) as? String
                if (defaultValue != null && defaultValue.contains("\${")) {
                    val interpolatedValue = resolvePropertyPlaceholders(defaultValue)
                    (property as KMutableProperty<*>).setter.call(bean, interpolatedValue)
                }
            }
        }
    }

    private fun resolvePropertyPlaceholders(value: String): String {
        val placeholders = mapOf("default-prop" to "ABC") // Replace with your property values
        var result = value
        for ((placeholder, replacement) in placeholders) {
            result = result.replace("\${$placeholder}", replacement)
        }
        return result
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class InterpolateDefaults
