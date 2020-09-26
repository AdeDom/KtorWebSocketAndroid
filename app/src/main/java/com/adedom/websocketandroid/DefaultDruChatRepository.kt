package com.adedom.websocketandroid

import com.chat.SendMessageRequest

interface DefaultDruChatRepository {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
