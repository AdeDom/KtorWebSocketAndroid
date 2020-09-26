package com.adedom.websocketandroid

import com.chat.SendMessageRequest

interface WebSocketDataSource {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
