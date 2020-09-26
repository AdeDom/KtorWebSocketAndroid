package com.adedom.websocketandroid.data.di

import com.adedom.websocketandroid.data.datasource.DataSourceProvider
import com.adedom.websocketandroid.data.datasource.DruChatDataSource
import com.adedom.websocketandroid.data.datasource.DruChatDataSourceImpl
import com.adedom.websocketandroid.data.repository.DefaultDruChatRepository
import com.adedom.websocketandroid.data.repository.DefaultDruChatRepositoryImpl
import org.koin.dsl.module

private val dataModule = module {

    single { DataSourceProvider() }

    single<DruChatDataSource> { DruChatDataSourceImpl(get()) }

    single<DefaultDruChatRepository> { DefaultDruChatRepositoryImpl(get()) }

}

val getDataModule = dataModule
