package com.net128.test.apps.configprops

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("info")
class InfoConfiguration (
        var additionalAttr: String? = null
)  : Info()

@JsonInclude(JsonInclude.Include.NON_NULL)
open class Info (
        var attr1: String = "attr1 data"
)


