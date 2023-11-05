package com.net128.test.apps.configprops

import com.net128.test.apps.lib.InfoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(InfoConfiguration::class)
@ConfigurationPropertiesScan(basePackageClasses = [InfoConfiguration::class])
class ConfigPropsApplication

fun main(args: Array<String>) {
	runApplication<ConfigPropsApplication>(*args)
}
