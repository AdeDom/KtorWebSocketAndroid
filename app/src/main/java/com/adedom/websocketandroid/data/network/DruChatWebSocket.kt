package com.adedom.websocketandroid.data.network

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.util.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

typealias ChatTypeAlias = (String) -> Unit

@KtorExperimentalAPI
class DruChatWebSocket {

    private var webSocket: WebSocketSession? = null

    suspend fun initialize(socket: ChatTypeAlias) {
        val client = HttpClient(OkHttp) {
            install(WebSockets)
        }

        client.wss(
            method = HttpMethod.Get,
            host = "dru-chat.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/ws",
        ) {
            webSocket = this
            try {
                incoming.consumeAsFlow().collect { frame ->
                    val response = (frame as Frame.Text).readText()
                    socket.invoke(response)
                }
            } finally {
                webSocket = null
            }
        }
    }

    suspend fun sendMessage(text: String) {
        webSocket?.outgoing?.send(Frame.Text(text))
    }

}
