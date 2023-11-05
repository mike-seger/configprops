package com.net128.test.apps.configprops

import com.net128.test.apps.lib.InfoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class InfoController(val infoConfiguration: InfoConfiguration) {
    @GetMapping("info")
    fun info(): InfoConfiguration {
        return infoConfiguration
    }
}
