package com.adedom.websocketandroid

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://adedom-chatv2.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }

    single<DefaultChatRepository> { DefaultChatRepositoryImpl(get()) }

    single { DataSourceProvider() }

    single<WebSocketDataSource> { WebSocketDataSourceImpl(get()) }

    single<DefaultWebSocketRepository> { DefaultWebSocketRepositoryImpl(get()) }

    viewModel { MainViewModel(get(), get()) }

}
