package com.adedom.websocketandroid.data.network

import com.chat.FetchChatResponse
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class ApiService {

    private val client = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }

    suspend fun fetchChat(): FetchChatResponse {
        return client.get("${BASE_URL}api/fetch-chat")
    }

    companion object {
        private const val BASE_URL = "https://adedom-chatv2.herokuapp.com/"
    }

}
