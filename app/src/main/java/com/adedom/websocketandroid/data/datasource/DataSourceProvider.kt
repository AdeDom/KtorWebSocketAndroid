package com.adedom.websocketandroid.data.datasource

import com.adedom.websocketandroid.data.network.ApiService
import com.adedom.websocketandroid.data.network.DruChatWebSocket

class DataSourceProvider {

    fun getProviderDruChatWebSocket(): DruChatWebSocket = DruChatWebSocket()

    fun getProviderApiService() = ApiService()

}
