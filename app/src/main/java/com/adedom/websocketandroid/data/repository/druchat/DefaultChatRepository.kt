package com.adedom.websocketandroid.data.repository.druchat

import com.chat.FetchChatResponse

interface DefaultChatRepository {

    suspend fun fetchChat(): FetchChatResponse

}
