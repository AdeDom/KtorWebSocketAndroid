package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.ChatTypeAlias
import com.chat.FetchChatResponse
import com.chat.SendMessageRequest

class DruChatDataSourceImpl(private val provider: DataSourceProvider) : DruChatDataSource {

    override suspend fun fetchChat(): FetchChatResponse {
        return provider.getProviderApiService().fetchChat()
    }

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
