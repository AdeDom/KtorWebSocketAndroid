package com.adedom.websocketandroid.presentation.main

data class MainState(
    val message: String = "",
    val isSendMessage: Boolean = false,
    val chat: String = "",
    val isChat: Boolean = false,
    val isToast: Boolean = false,
    val messageToast: String = "",
    val loading: Boolean = false,
)
