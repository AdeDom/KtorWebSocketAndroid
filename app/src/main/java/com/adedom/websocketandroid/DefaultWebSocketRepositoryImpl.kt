package com.adedom.websocketandroid

import com.chat.SendMessageRequest

class DefaultWebSocketRepositoryImpl(
    private val dataSource: DruChatDataSource
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
