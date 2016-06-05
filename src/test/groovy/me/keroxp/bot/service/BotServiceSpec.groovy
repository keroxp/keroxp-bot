package me.keroxp.bot.service

import com.linecorp.bot.client.LineBotClient
import com.linecorp.bot.model.content.Content
import com.linecorp.bot.model.content.ContentType
import com.linecorp.bot.model.content.RecipientType
import com.linecorp.bot.model.content.TextContent
import me.keroxp.bot.domain.response.BotResponseStatus
import spock.lang.Specification

class BotServiceSpec extends Specification {
    private LineBotClient stubBotClient
    private BotService service
    void setup() {
        stubBotClient = Stub(LineBotClient) {
            sendText(_,_) >> {
                // do nothing
            }
        }
        service = new BotService()
        service.@botClient = stubBotClient
    }
    def "handle should be return echoMessage when TextContent received"() {
        setup:
        def text = new TextContent(
                "id",
                "fromId",
                ContentType.TEXT,
                RecipientType.USER,
                "text message"
        )
        when:
        def res = service.handle(text)
        then:
        res.receiverId == "fromId"
        res.message == "text message"
    }
    def "handle should be return error message when unsupported content received"() {
        setup:
        def dummy = new Content() {}
        when:
        def res = service.handle(dummy)
        then:
        res.status == BotResponseStatus.ERROR
    }
}
