package com.adedom.websocketandroid

import com.chat.ChatResponse

data class MainState(
    val message: String = "",
    val isSendMessage: Boolean = false,
    val chat: ChatResponse? = null,
    val isChat: Boolean = false,
    val loading: Boolean = false,
)
