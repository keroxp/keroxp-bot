package me.keroxp.bot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
@Controller
open class Application : SpringApplication() {
    @RequestMapping("/")
    @ResponseBody
    fun hello(): String = "hello, kotlin!"
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
