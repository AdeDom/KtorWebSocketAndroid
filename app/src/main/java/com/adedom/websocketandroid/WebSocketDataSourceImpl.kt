package com.adedom.websocketandroid

import com.chat.SendMessageRequest

class WebSocketDataSourceImpl(private val provider: DataSourceProvider) : WebSocketDataSource {

    override suspend fun initialize(socket: ChatTypeAlias) {
        provider.getProviderDruChatWebSocket().initialize(socket)
    }

    override suspend fun sendMessage(sendMessage: SendMessageRequest) {
        provider.getProviderDruChatWebSocket().sendMessage(sendMessage)
    }

    override fun closeWebSocket() {
        provider.getProviderDruChatWebSocket().closeWebSocket()
    }

}
