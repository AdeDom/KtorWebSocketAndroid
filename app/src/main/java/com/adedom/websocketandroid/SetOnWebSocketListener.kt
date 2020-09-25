package com.adedom.websocketandroid

import com.chat.ChatResponse

interface SetOnWebSocketListener {
    fun onWebSocket(chat: ChatResponse)
}
