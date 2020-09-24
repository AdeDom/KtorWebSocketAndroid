package com.adedom.websocketandroid

import com.chat.ChatResponse
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

class WebSocketDruChat(private val listener: SetOnWebSocketListener) {

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }

    suspend fun initialize() {
        client.wss(
            method = HttpMethod.Get,
            host = "adedom-chatv2.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/webSocket/chatv2",
        ) {

            incoming.receiveAsFlow()
                .collect { frame ->
                    val text = (frame as Frame.Text).readText()
                    val chat = Gson().fromJson(text, ChatResponse::class.java)
                    listener.onWebSocket(chat)
                }

        }

    }

    fun closeWebSocket() {
        client.close()
    }

    interface SetOnWebSocketListener {
        fun onWebSocket(chat: ChatResponse)
    }

}
