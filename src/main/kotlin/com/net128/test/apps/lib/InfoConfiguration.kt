package com.net128.test.apps.lib

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@Configuration
@ConfigurationProperties(prefix = "info")
@ComponentScan(basePackageClasses = [InfoConfiguration::class])
@ConfigurationPropertiesScan
class InfoConfiguration (
    var attr1: String? = null,
    var attr2: String? = null
)

@Configuration
@PropertySource("classpath:/com/net128/test/apps/lib/info.yaml")
@PropertySource("classpath:com/net128/test/apps/lib/info.yaml")
@PropertySource("classpath:info.yaml")
@PropertySource("classpath:/info.yaml")
class InfoConfigurationDefault


