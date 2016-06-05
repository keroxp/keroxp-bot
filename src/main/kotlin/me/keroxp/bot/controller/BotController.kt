package me.keroxp.bot.controller

import com.linecorp.bot.client.exception.LineBotAPIException
import com.linecorp.bot.spring.boot.annotation.LineBotMessages
import me.keroxp.bot.domain.response.BotResponse
import me.keroxp.bot.service.BotService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.linecorp.bot.model.callback.Event as LineBotEvent

@RestController
class BotController {

    @Autowired
    lateinit private var botService: BotService

    private val log = LoggerFactory.getLogger(BotController::class.java)

    @RequestMapping("/callback")
    @Throws(LineBotAPIException::class)
    fun callback(@LineBotMessages events: List<LineBotEvent>): List<BotResponse> {
        log.debug("$events")
        return events.map { botService.handle(it.content) }
    }

}