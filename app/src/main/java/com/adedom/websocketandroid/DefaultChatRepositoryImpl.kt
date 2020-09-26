package com.adedom.websocketandroid

import com.chat.FetchChatResponse

class DefaultChatRepositoryImpl(private val api: ApiService) : DefaultChatRepository {

    override suspend fun fetchChat(): FetchChatResponse = api.fetchChat()

}
