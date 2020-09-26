package com.adedom.websocketandroid

import com.chat.FetchChatResponse
import retrofit2.http.GET

interface ApiService {

    @GET("api/fetch-chat")
    suspend fun fetchChat(): FetchChatResponse

}
