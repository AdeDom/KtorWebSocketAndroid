package com.adedom.websocketandroid.data.network

import com.chat.ChatResponse
import com.chat.SendMessageRequest
import com.chat.fromJson
import com.chat.toJson
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

typealias ChatTypeAlias = (ChatResponse) -> Unit

class DruChatWebSocket {

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }

    private var webSocket: WebSocketSession? = null

    suspend fun initialize(socket: ChatTypeAlias) {
        client.wss(
            method = HttpMethod.Get,
            host = "adedom-chatv2.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/webSocket/dru-chat",
        ) {
            webSocket = this
            try {
                incoming.consumeAsFlow().collect { frame ->
                    val response = (frame as Frame.Text).fromJson<ChatResponse>()
                    socket.invoke(response)
                }
            } finally {
                webSocket = null
            }
        }
    }

    suspend fun sendMessage(sendMessage: SendMessageRequest) {
        webSocket?.outgoing?.send(sendMessage.toJson())
    }

    fun closeWebSocket() {
        client.close()
    }

}
