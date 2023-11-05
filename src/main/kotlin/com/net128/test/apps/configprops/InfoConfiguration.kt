package com.net128.test.apps.configprops

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@JsonInclude(JsonInclude.Include.NON_NULL)
@Configuration
@ConfigurationProperties("info")
class InfoConfiguration (
        var additionalAttr: String? = null
)  : Info()

open class Info (
        var attr1: String = "attr1 data"
)


