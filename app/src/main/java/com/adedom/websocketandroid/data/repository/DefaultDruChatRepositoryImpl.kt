package com.adedom.websocketandroid.data.repository

import com.adedom.websocketandroid.data.datasource.DruChatDataSource
import com.adedom.websocketandroid.data.network.ChatTypeAlias
import com.chat.FetchChatResponse
import com.chat.SendMessageRequest

class DefaultDruChatRepositoryImpl(
    private val dataSource: DruChatDataSource
) : DefaultDruChatRepository {

    override suspend fun fetchChat(): FetchChatResponse {
        return dataSource.fetchChat()
    }

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
