package me.keroxp.bot.domain.response

data class TextResponse(
        val receiverId: String,
        val message: String,
        override val status: BotResponseStatus = BotResponseStatus.SUCCESS
) : BotResponse