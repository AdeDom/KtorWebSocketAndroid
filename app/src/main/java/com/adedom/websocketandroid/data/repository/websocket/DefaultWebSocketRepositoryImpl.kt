package com.adedom.websocketandroid.data.repository.websocket

import com.adedom.websocketandroid.data.datasource.websocket.WebSocketDataSource
import com.adedom.websocketandroid.data.network.websocket.ChatTypeAlias
import com.chat.SendMessageRequest

class DefaultWebSocketRepositoryImpl(
    private val dataSource: WebSocketDataSource
) : DefaultWebSocketRepository {

    override suspend fun initialize(socket: ChatTypeAlias) {
        dataSource.initialize(socket)
    }

    override suspend fun sendMessage(sendMessage: SendMessageRequest) {
        dataSource.sendMessage(sendMessage)
    }

    override fun closeWebSocket() {
        dataSource.closeWebSocket()
    }

}
