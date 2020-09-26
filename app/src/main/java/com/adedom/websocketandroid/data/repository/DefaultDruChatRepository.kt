package com.adedom.websocketandroid.data.repository

import com.adedom.websocketandroid.data.network.ChatTypeAlias
import com.chat.FetchChatResponse
import com.chat.SendMessageRequest

interface DefaultDruChatRepository {

    suspend fun fetchChat(): FetchChatResponse

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
