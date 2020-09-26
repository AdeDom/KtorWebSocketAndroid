package com.adedom.websocketandroid

import com.chat.FetchChatResponse

class DefaultChatRepositoryImpl(private val dataSource: DruChatDataSource) : DefaultChatRepository {

    override suspend fun fetchChat(): FetchChatResponse = dataSource.fetchChat()

}
