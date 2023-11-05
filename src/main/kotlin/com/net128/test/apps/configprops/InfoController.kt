package com.net128.test.apps.configprops

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class InfoController(val infoConfiguration: InfoConfiguration) {
    @GetMapping("info")
    @ResponseBody
    fun info(): Info {
        return Converter.convert<InfoConfiguration, Info>(infoConfiguration)
    }
}