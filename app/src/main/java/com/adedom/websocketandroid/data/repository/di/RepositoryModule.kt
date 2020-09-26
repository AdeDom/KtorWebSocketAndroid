package com.adedom.websocketandroid.data.repository.di

import com.adedom.websocketandroid.data.repository.druchat.DefaultChatRepository
import com.adedom.websocketandroid.data.repository.druchat.DefaultChatRepositoryImpl
import com.adedom.websocketandroid.data.repository.websocket.DefaultWebSocketRepository
import com.adedom.websocketandroid.data.repository.websocket.DefaultWebSocketRepositoryImpl
import org.koin.dsl.module

private val repositoryModule = module {

    single<DefaultChatRepository> { DefaultChatRepositoryImpl(get()) }

    single<DefaultWebSocketRepository> { DefaultWebSocketRepositoryImpl(get()) }

}

val getRepositoryModule = repositoryModule
