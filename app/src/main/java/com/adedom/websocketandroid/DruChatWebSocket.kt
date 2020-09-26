package com.adedom.websocketandroid

import com.chat.ChatResponse
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

typealias ChatTypeAlias = (ChatResponse) -> Unit

object DruChatWebSocket {

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }

    private suspend fun webSocket(socket: suspend DefaultClientWebSocketSession.() -> Unit) {
        client.wss(
            method = HttpMethod.Get,
            host = "dru-chat.herokuapp.com",
            port = DEFAULT_PORT,
            path = "/ws",
        ) {
            socket.invoke(this)
        }
    }

    suspend fun outgoingSendFrameText(text: String) {
        webSocket {
            outgoing.send(Frame.Text(text))
        }
    }

    suspend fun incomingReceiveFrameText(text: (String) -> Unit) {
        webSocket {
            incoming.receiveAsFlow()
                .collect { frame ->
                    val readText = (frame as Frame.Text).readText()
                    text.invoke(readText)
                }
        }
    }

    fun closeWebSocket() {
        client.close()
    }

}
