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

    private suspend fun webSocket(socket: suspend DefaultClientWebSocketSession.() -> Unit) {
        client.wss(
            method = HttpMethod.Get,
            host = "adedom-chatv2.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/webSocket/dru-chat",
        ) {
            socket.invoke(this)
        }
    }

    private suspend fun outgoingSendFrameText(text: Frame.Text) {
        webSocket {
            outgoing.send(text)
        }
    }

    private suspend fun incomingReceiveFrameText(text: (Frame.Text) -> Unit) {
        webSocket {
            incoming.consumeAsFlow()
                .collect { frame ->
                    text.invoke(frame as Frame.Text)
                }
        }
    }

    suspend fun initialize(socket: ChatTypeAlias) {
        incomingReceiveFrameText {
            val response = it.fromJson<ChatResponse>()
            socket.invoke(response)
        }
    }

    suspend fun sendMessage(sendMessage: SendMessageRequest) {
        outgoingSendFrameText(sendMessage.toJson())
    }

    fun closeWebSocket() {
        client.close()
    }

}
