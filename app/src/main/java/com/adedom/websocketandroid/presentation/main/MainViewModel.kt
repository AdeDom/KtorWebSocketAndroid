package com.adedom.websocketandroid.presentation.main

import com.adedom.websocketandroid.base.BaseViewModel
import com.adedom.websocketandroid.data.repository.DefaultDruChatRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: DefaultDruChatRepository
) : BaseViewModel<MainState>(MainState()) {

    fun initialize() {
        launch {
            repository.initialize {
                setState { copy(isChat = true, chat = it) }
                setState { copy(isChat = false) }
            }
        }
    }

    fun setStateMessage(message: String) {
        setState { copy(message = message) }
    }

    fun sendMessage() {
        launch {
            try {
                val message = state.value?.message.orEmpty()
                setState { copy(message = "", isSendMessage = true, loading = true) }
                setState { copy(isSendMessage = false) }
                repository.sendMessage(message)
                setState { copy(loading = false) }
            } catch (e: Throwable) {
                setError(e)
                setState { copy(loading = true) }
            }
        }
    }

}
