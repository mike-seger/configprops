package com.net128.test.apps.configprops

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class ConfigpropsApplication

fun main(args: Array<String>) {
	runApplication<ConfigpropsApplication>(*args)
}
