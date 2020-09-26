package com.adedom.websocketandroid.data.repository.druchat

import com.adedom.websocketandroid.data.datasource.druchat.DruChatDataSource
import com.chat.FetchChatResponse

class DefaultChatRepositoryImpl(private val dataSource: DruChatDataSource) : DefaultChatRepository {

    override suspend fun fetchChat(): FetchChatResponse = dataSource.fetchChat()

}
