package com.adedom.websocketandroid

data class MainState(
    val message: String = "",
    val isSendMessage: Boolean = false,
    val loading: Boolean = false,
)
