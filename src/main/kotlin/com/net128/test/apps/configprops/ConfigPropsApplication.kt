package com.net128.test.apps.configprops

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@SpringBootApplication
class ConfigPropsApplication{

}

fun main(args: Array<String>) {
	runApplication<ConfigPropsApplication>(*args)
}
