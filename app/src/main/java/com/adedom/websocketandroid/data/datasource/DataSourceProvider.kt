package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.ApiService
import com.adedom.websocketandroid.data.network.DruChatWebSocket

class DataSourceProvider(
    private val webSocket: DruChatWebSocket,
    private val apiService: ApiService,
) {

    fun getProviderDruChatWebSocket(): DruChatWebSocket = webSocket

    fun getProviderApiService(): ApiService = apiService

}
