package com.adedom.websocketandroid.data.datasource.di

import com.adedom.websocketandroid.data.datasource.DataSourceProvider
import com.adedom.websocketandroid.data.datasource.druchat.DruChatDataSource
import com.adedom.websocketandroid.data.datasource.druchat.DruChatDataSourceImpl
import com.adedom.websocketandroid.data.datasource.websocket.WebSocketDataSource
import com.adedom.websocketandroid.data.datasource.websocket.WebSocketDataSourceImpl
import org.koin.dsl.module

private val dataSource = module {

    single { DataSourceProvider() }

    single<DruChatDataSource> { DruChatDataSourceImpl(get()) }

    single<WebSocketDataSource> { WebSocketDataSourceImpl(get()) }

}

val getDataSource = dataSource
