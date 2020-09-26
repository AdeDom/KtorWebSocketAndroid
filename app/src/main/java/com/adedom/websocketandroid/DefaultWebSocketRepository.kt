package com.adedom.websocketandroid

import com.chat.SendMessageRequest

interface DefaultWebSocketRepository {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
