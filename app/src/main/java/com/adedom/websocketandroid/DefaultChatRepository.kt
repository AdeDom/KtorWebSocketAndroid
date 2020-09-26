package com.adedom.websocketandroid

import com.chat.FetchChatResponse

interface DefaultChatRepository {

    suspend fun fetchChat(): FetchChatResponse

}
