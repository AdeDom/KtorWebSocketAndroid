package com.adedom.websocketandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.wss
import io.ktor.http.DEFAULT_PORT
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import io.ktor.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@KtorExperimentalAPI
class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = HttpClient(OkHttp) {
            install(WebSockets)
        }

        launch {
            client.wss(
                method = HttpMethod.Get,
                host = "dru-chat.herokuapp.com",
                port = DEFAULT_PORT,
                path = "/ws"
            ) {
                for (frame in incoming) {
                    val text = (frame as Frame.Text).readText()
                    tv_message.append("$text\n")
                }
            }
        }
    }

}
