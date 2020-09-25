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

class WebSocketDruChat(private val listener: SetOnWebSocketListener) {

    suspend fun initialize() {
        webSocket {
            incoming.receiveAsFlow()
                .collect { frame ->
                    val text = (frame as Frame.Text).readText()
                    listener.onWebSocket(ChatResponse(message = text))
                }
        }
    }

}

private val client = HttpClient(OkHttp) {
    install(WebSockets)
}

private suspend fun webSocket(socket:suspend DefaultClientWebSocketSession.()->Unit){
    client.wss(
        method = HttpMethod.Get,
        host = "dru-chat.herokuapp.com",
        port = DEFAULT_PORT,
        path = "/ws",
    ) {
        socket.invoke(this)
    }
}

fun closeWebSocket(){
    client.close()
}

suspend fun sendMessage(sendMessage: SendMessageRequest) {
    webSocket {
        outgoing.send(Frame.Text(sendMessage.message))
    }
}
