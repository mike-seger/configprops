package com.net128.test.apps.configprops

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.core.env.PropertySource
import org.springframework.core.io.Resource
import org.springframework.core.io.support.EncodedResource
import org.springframework.core.io.support.PropertySourceFactory
import java.util.*

class YamlPropertySourceFactory : PropertySourceFactory {
    override fun createPropertySource(name: String?, resource: EncodedResource): PropertySource<*> {
        val loadedProperties = loadYamlIntoProperties(resource.resource)
        return PropertiesPropertySource(name ?: resource.resource.filename ?: "", loadedProperties)
    }

    private fun loadYamlIntoProperties(resource: Resource): Properties {
        val factory = YamlPropertiesFactoryBean()
        factory.setResources(resource)
        factory.afterPropertiesSet()
        return factory.`object` ?: Properties()
    }
}
