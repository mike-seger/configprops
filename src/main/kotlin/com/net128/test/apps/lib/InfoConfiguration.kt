package com.net128.test.apps.lib

import com.net128.test.apps.configprops.YamlPropertySourceFactory
import org.springframework.boot.context.properties.ConfigurationProperties

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@ConfigurationProperties(prefix = "info")
@PropertySource(value = ["classpath:/com/net128/test/apps/lib/info.yaml"], factory = YamlPropertySourceFactory::class)
class InfoConfiguration (
    var attr1: String? = null,
    var attr2: String? = null
)
