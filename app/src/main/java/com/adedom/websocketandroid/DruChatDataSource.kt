package com.adedom.websocketandroid

import com.chat.FetchChatResponse

interface DruChatDataSource {

    suspend fun fetchChat(): FetchChatResponse

}
