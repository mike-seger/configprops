package com.net128.test.apps.configprops

import ExcludeFieldsFilterSerializer
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@JsonInclude(JsonInclude.Include.NON_NULL)
@Configuration
@ConfigurationProperties("info")
@JsonSerialize(using = ExcludeFieldsFilterSerializer::class)
data class Info (
       var attr1: String = "attr1 data"
)
