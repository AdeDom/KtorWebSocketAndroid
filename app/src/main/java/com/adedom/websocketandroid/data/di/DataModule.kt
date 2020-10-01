package com.adedom.websocketandroid.data.di

import com.adedom.websocketandroid.data.datasource.DataSourceProvider
import com.adedom.websocketandroid.data.datasource.DruChatDataSource
import com.adedom.websocketandroid.data.datasource.DruChatDataSourceImpl
import com.adedom.websocketandroid.data.network.DruChatWebSocket
import com.adedom.websocketandroid.data.repository.DefaultDruChatRepository
import com.adedom.websocketandroid.data.repository.DefaultDruChatRepositoryImpl
import io.ktor.util.*
import org.koin.dsl.module

@KtorExperimentalAPI
private val dataModule = module {

    single { DruChatWebSocket() }

    single { DataSourceProvider(get()) }

    single<DruChatDataSource> { DruChatDataSourceImpl(get()) }

    single<DefaultDruChatRepository> { DefaultDruChatRepositoryImpl(get()) }

}

@KtorExperimentalAPI
val getDataModule = dataModule
