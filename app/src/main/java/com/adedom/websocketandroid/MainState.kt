package com.adedom.websocketandroid

import com.chat.ChatResponse

data class MainState(
    val message: String = "",
    val isSendMessage: Boolean = false,
    val chat: ChatResponse? = null,
    val isChat: Boolean = false,
    val isToast: Boolean = false,
    val messageToast: String = "",
    val loading: Boolean = false,
)
