package com.net128.test.apps.lib

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@Configuration
@ConfigurationProperties(prefix = "info")
@PropertySource("classpath:/com/net128/test/apps/lib/infocontroller.yaml")
@PropertySource("classpath:com/net128/test/apps/lib/infocontroller.yaml")
@PropertySource("classpath:infocontroller.yaml")
@PropertySource("classpath:/infocontroller.yaml")
class InfoConfiguration (
        var attr1: String = "class attr1 data \${default-prop}",
        var attr2: String = "class attr2 data \${default-prop}"
)
