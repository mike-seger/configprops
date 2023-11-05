package com.net128.test.apps.configprops

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("info")
//@JsonSerialize(using = SpringBeanSerializer::class)
data class InfoConfiguration (
    var attr1: String = "attr1 data"
)
