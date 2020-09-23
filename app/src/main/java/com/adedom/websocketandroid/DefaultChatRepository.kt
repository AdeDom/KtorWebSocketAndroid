package com.adedom.websocketandroid

import com.chat.BaseResponse
import com.chat.FetchChatResponse
import com.chat.SendMessageRequest

interface DefaultChatRepository {

    suspend fun fetchChat(): FetchChatResponse

    suspend fun sendMessage(sendMessage: SendMessageRequest): BaseResponse

}
