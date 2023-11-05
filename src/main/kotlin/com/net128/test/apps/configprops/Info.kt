package com.net128.test.apps.configprops

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("info")
data class Info(
    var attr1: String = "attr1 data",
)