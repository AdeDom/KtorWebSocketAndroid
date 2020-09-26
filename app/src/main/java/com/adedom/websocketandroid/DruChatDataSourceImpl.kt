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

class DruChatDataSourceImpl : DruChatDataSource {

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

    private suspend fun outgoingSendFrameText(text: String) {
        webSocket {
            outgoing.send(Frame.Text(text))
        }
    }

    private suspend fun incomingReceiveFrameText(text: (String) -> Unit) {
        webSocket {
            incoming.receiveAsFlow()
                .collect { frame ->
                    val readText = (frame as Frame.Text).readText()
                    text.invoke(readText)
                }
        }
    }

    override suspend fun initialize(socket: ChatTypeAlias) {
        incomingReceiveFrameText {
            socket.invoke(ChatResponse(message = it))
        }
    }

    override suspend fun sendMessage(sendMessage: SendMessageRequest) {
        outgoingSendFrameText(sendMessage.message)
    }

    override fun closeWebSocket() {
        client.close()
    }

}
