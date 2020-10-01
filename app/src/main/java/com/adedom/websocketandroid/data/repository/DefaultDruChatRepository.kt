package com.adedom.websocketandroid.data.repository

import com.adedom.websocketandroid.data.network.ChatTypeAlias

interface DefaultDruChatRepository {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(text: String)

    fun closeWebSocket()

}
