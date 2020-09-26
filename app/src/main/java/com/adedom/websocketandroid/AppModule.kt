package com.adedom.websocketandroid

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { DataSourceProvider() }

    single<DruChatDataSource> { DruChatDataSourceImpl(get()) }

    single<DefaultChatRepository> { DefaultChatRepositoryImpl(get()) }

    single<WebSocketDataSource> { WebSocketDataSourceImpl(get()) }

    single<DefaultWebSocketRepository> { DefaultWebSocketRepositoryImpl(get()) }

    viewModel { MainViewModel(get(), get()) }

}
