package com.adedom.websocketandroid

import com.chat.ChatResponse
import com.chat.SendMessageRequest

class DefaultDruChatRepositoryImpl : DefaultDruChatRepository {

    override suspend fun initialize(socket: ChatTypeAlias) {
        DruChatWebSocket.incomingReceiveFrameText {
            socket.invoke(ChatResponse(message = it))
        }
    }

    override suspend fun sendMessage(sendMessage: SendMessageRequest) {
        DruChatWebSocket.outgoingSendFrameText(sendMessage.message)
    }

    override fun closeWebSocket() {
        DruChatWebSocket.closeWebSocket()
    }

}
