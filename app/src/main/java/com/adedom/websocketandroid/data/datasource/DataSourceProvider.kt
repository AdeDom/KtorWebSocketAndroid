package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.websocket.DruChatWebSocket
import com.adedom.websocketandroid.data.network.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceProvider {

    fun getProviderDruChatWebSocket(): DruChatWebSocket = DruChatWebSocket()

    fun getProviderApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://adedom-chatv2.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

}
