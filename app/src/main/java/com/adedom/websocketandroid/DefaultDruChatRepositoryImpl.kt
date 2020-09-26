package com.adedom.websocketandroid

import com.chat.SendMessageRequest

class DefaultDruChatRepositoryImpl : DefaultDruChatRepository {

    override suspend fun initialize(socket: ChatTypeAlias) {
        WebSocketDruChat.initialize(socket)
    }

    override suspend fun sendMessage(sendMessage: SendMessageRequest) {
        WebSocketDruChat.sendMessage(sendMessage)
    }

    override fun closeWebSocket() {
        WebSocketDruChat.closeWebSocket()
    }

}
