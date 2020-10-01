package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.ChatTypeAlias

interface DruChatDataSource {

    suspend fun initialize(socket: ChatTypeAlias)

    suspend fun sendMessage(text: String)

    fun closeWebSocket()

}
