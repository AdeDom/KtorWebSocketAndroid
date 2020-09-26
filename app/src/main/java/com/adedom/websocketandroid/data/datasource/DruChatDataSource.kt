package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.ChatTypeAlias
import com.chat.FetchChatResponse
import com.chat.SendMessageRequest

interface DruChatDataSource {

    suspend fun fetchChat(): FetchChatResponse

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(sendMessage: SendMessageRequest)

    fun closeWebSocket()

}
