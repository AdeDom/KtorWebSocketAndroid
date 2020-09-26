package com.adedom.websocketandroid

import com.chat.ChatResponse
import com.chat.SendMessageRequest

typealias ChatTypeAlias = (ChatResponse) -> Unit

interface DruChatDataSource {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
