package com.adedom.websocketandroid.data.datasource.druchat

import com.adedom.websocketandroid.data.datasource.DataSourceProvider
import com.chat.FetchChatResponse

class DruChatDataSourceImpl(private val provider: DataSourceProvider) : DruChatDataSource {

    override suspend fun fetchChat(): FetchChatResponse {
        return provider.getProviderApiService().fetchChat()
    }

}
