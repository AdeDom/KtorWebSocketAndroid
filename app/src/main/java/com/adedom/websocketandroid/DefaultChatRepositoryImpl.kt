package com.adedom.websocketandroid

import com.chat.BaseResponse
import com.chat.FetchChatResponse
import com.chat.SendMessageRequest

class DefaultChatRepositoryImpl(private val api: ApiService) : DefaultChatRepository {

    override suspend fun fetchChat(): FetchChatResponse = api.fetchChat()

    override suspend fun sendMessage(sendMessage: SendMessageRequest): BaseResponse {
        return api.sendMessage(sendMessage)
    }

}
