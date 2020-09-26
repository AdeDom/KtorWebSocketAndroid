package com.adedom.websocketandroid.data.datasource.druchat

import com.chat.FetchChatResponse

interface DruChatDataSource {

    suspend fun fetchChat(): FetchChatResponse

}
