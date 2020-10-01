package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.ChatTypeAlias
import io.ktor.util.*

@KtorExperimentalAPI
class DruChatDataSourceImpl(private val provider: DataSourceProvider) : DruChatDataSource {

    override suspend fun initialize(socket: ChatTypeAlias) {
        provider.getProviderDruChatWebSocket().initialize(socket)
    }

    override suspend fun sendMessage(text: String) {
        provider.getProviderDruChatWebSocket().sendMessage(text)
    }

    override fun closeWebSocket() {
        provider.getProviderDruChatWebSocket().closeWebSocket()
    }

}
