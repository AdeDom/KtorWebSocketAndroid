package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.DruChatWebSocket
import io.ktor.util.*

@KtorExperimentalAPI
class DataSourceProvider(
    private val webSocket: DruChatWebSocket,
) {

    fun getProviderDruChatWebSocket(): DruChatWebSocket = webSocket

}
