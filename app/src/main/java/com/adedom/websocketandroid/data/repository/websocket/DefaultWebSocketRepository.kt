package com.adedom.websocketandroid.data.repository.websocket

import com.adedom.websocketandroid.data.network.websocket.ChatTypeAlias
import com.chat.SendMessageRequest

interface DefaultWebSocketRepository {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
