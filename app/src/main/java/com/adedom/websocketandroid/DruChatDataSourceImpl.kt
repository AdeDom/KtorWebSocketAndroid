package com.adedom.websocketandroid

import com.chat.FetchChatResponse

class DruChatDataSourceImpl(private val provider: DataSourceProvider) : DruChatDataSource {

    override suspend fun fetchChat(): FetchChatResponse {
        return provider.getProviderApiService().fetchChat()
    }

}
