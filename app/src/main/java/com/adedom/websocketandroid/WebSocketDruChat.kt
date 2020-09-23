package com.adedom.websocketandroid

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
            host = "dru-chat.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/ws",
        ) {

            incoming.receiveAsFlow()
                .collect { frame ->
                    val message = (frame as Frame.Text).readText()
                    listener.onWebSocket(message)
                }

        }

    }

    fun closeWebSocket() {
        client.close()
    }

    interface SetOnWebSocketListener {
        fun onWebSocket(message: String)
    }

}
