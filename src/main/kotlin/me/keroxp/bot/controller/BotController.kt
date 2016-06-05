import com.linecorp.bot.client.exception.LineBotAPIException
import com.linecorp.bot.spring.boot.annotation.LineBotMessages
import me.keroxp.bot.domain.response.BotResponse
import me.keroxp.bot.service.BotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.linecorp.bot.model.callback.Event as LineBotEvent

@RestController
class BotController {

    @Autowired
    lateinit private var botService: BotService

    @RequestMapping("/callback")
    @Throws(LineBotAPIException::class)
    fun callback(@LineBotMessages events: List<LineBotEvent>): () -> List<BotResponse> {
        return {
            events.map { botService.handle(it.content) }
        }
    }
}