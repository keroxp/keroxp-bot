package me.keroxp.bot.service

import com.linecorp.bot.client.LineBotClient
import com.linecorp.bot.model.content.AbstractContent
import com.linecorp.bot.model.content.Content
import com.linecorp.bot.model.content.TextContent
import me.keroxp.bot.domain.response.BotResponse
import me.keroxp.bot.domain.response.BotResponseStatus
import me.keroxp.bot.domain.response.ErrorResponse
import me.keroxp.bot.domain.response.TextResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("botService")
class BotService {
    @Autowired
    lateinit private var botClient: LineBotClient

    fun handle(content: TextContent): TextResponse {
        botClient.sendText(content.from, content.text)
        return TextResponse(content.from, content.text)
    }

    fun handle(content: Content): BotResponse {
        try {
            (content as AbstractContent).let {
                val rid = it.from
                val msg = "this type of it(${it.contentType.name}(${it.contentType.code})) was not supported"
                botClient.sendText(rid, msg)
                return TextResponse(rid, msg, BotResponseStatus.ERROR)
            }
        } catch (e: Exception) {
            return ErrorResponse(e)
        }
    }
}