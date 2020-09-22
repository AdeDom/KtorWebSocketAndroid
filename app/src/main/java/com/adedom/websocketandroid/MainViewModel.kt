package com.adedom.websocketandroid

import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel<MainState>(MainState()),
    WebSocketDruChat.SetOnWebSocketListener {

    init {
        launch {
            WebSocketDruChat(this@MainViewModel).initialize()
        }
    }

    override fun onWebSocket(message: String) {
        try {
            setState { copy(message = message) }
        } catch (e: Throwable) {
            setError(e)
        }
    }

}
