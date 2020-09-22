package com.adedom.websocketandroid

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*

class WebSocketDruChat(private val listener: SetOnWebSocketListener) {

    suspend fun initialize() {
        val client = HttpClient(OkHttp) {
            install(WebSockets)
        }

        client.wss(
            method = HttpMethod.Get,
            host = "dru-chat.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/ws"
        ) {
            for (frame in incoming) {
                val message = (frame as Frame.Text).readText()
                listener.onWebSocket(message)
            }
        }

    }

    interface SetOnWebSocketListener {
        fun onWebSocket(message: String)
    }

}
