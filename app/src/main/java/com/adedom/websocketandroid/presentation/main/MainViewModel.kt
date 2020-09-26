package com.adedom.websocketandroid.presentation.main

import com.adedom.websocketandroid.base.BaseViewModel
import com.adedom.websocketandroid.data.repository.DefaultDruChatRepository
import com.chat.SendMessageRequest
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

    fun fetchChat() {
        launch {
            try {
                setState { copy(loading = true) }
                val response = repository.fetchChat()
                response.chat.forEach {
                    setState { copy(isChat = true, chat = it) }
                }
                setState { copy(isToast = true, messageToast = response.message) }
                setState { copy(isChat = false, loading = false, isToast = false) }
            } catch (e: Throwable) {
                setState { copy(loading = false) }
                setError(e)
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
                val request = SendMessageRequest(message = message)
                setState { copy(message = "", isSendMessage = true, loading = true) }
                setState { copy(isSendMessage = false) }
                repository.sendMessage(request)
                setState { copy(loading = false) }
            } catch (e: Throwable) {
                setError(e)
                setState { copy(loading = true) }
            }
        }
    }

    override fun coroutineExceptionHandler() = initialize()

    override fun onCleared() {
        super.onCleared()
        repository.closeWebSocket()
    }

}
