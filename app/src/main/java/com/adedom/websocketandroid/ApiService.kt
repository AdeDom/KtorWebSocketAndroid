package com.adedom.websocketandroid

import com.chat.BaseResponse
import com.chat.FetchChatResponse
import com.chat.SendMessageRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/fetch-chat")
    suspend fun fetchChat(): FetchChatResponse

    @POST("api/send-message")
    suspend fun sendMessage(@Body sendMessage: SendMessageRequest): BaseResponse

}
