package com.adedom.websocketandroid

import com.chat.ChatResponse
import com.chat.SendMessageRequest
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

typealias ChatTypeAlias = (ChatResponse) -> Unit

object WebSocketDruChat {

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }

    suspend fun initialize(socket: ChatTypeAlias) {
        client.wss(
            method = HttpMethod.Get,
            host = "dru-chat.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/ws",
        ) {
            incoming.receiveAsFlow()
                .collect { frame ->
                    val text = (frame as Frame.Text).readText()
                    socket.invoke(ChatResponse(message = text))
                }
        }
    }

    suspend fun sendMessage(sendMessage: SendMessageRequest) {
        client.wss(
            method = HttpMethod.Get,
            host = "dru-chat.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/ws",
        ) {
            outgoing.send(Frame.Text(sendMessage.message))
        }
    }

    fun closeWebSocket() {
        client.close()
    }

}
