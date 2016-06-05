package me.keroxp.bot.domain.response

class ErrorResponse(val description: String) : BotResponse {
    constructor(e: Exception) : this(e.message ?: "")

    override val status: BotResponseStatus = BotResponseStatus.ERROR
}