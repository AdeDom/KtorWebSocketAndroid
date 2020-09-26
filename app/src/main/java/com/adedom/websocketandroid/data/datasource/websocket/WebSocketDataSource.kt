package com.adedom.websocketandroid.data.datasource.websocket

import com.adedom.websocketandroid.data.network.websocket.ChatTypeAlias
import com.chat.SendMessageRequest

interface WebSocketDataSource {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
