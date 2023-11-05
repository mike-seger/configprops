package com.net128.test.apps.configprops

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConfigPropsApplication

fun main(args: Array<String>) {
	runApplication<ConfigPropsApplication>(*args)
}
