package com.adedom.websocketandroid.data.repository

import com.adedom.websocketandroid.data.datasource.DruChatDataSource
import com.adedom.websocketandroid.data.network.ChatTypeAlias

class DefaultDruChatRepositoryImpl(
    private val dataSource: DruChatDataSource
) : DefaultDruChatRepository {

    override suspend fun initialize(socket: ChatTypeAlias) {
        dataSource.initialize(socket)
    }

    override suspend fun sendMessage(text: String) {
        dataSource.sendMessage(text)
    }

}
