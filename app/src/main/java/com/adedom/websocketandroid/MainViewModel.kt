package com.adedom.websocketandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chat.ChatResponse
import com.chat.SendMessageRequest
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: DefaultChatRepository
) : BaseViewModel<MainState>(MainState()),
    SetOnWebSocketListener {

    private val _callApi = MutableLiveData<String>()
    val callApi: LiveData<String>
        get() = _callApi

    fun initialize() {
        launch {
           WebSocketDruChat(this@MainViewModel).apply { initialize() }
        }
    }

    override fun onWebSocket(chat: ChatResponse) {
        setState { copy(isChat = true, chat = chat) }
        setState { copy(isChat = false) }
    }

    fun fetchChat() {
        launch {
            try {
                setState { copy(loading = true) }
                val response = repository.fetchChat()
                response.chat.forEach {
                    setState { copy(isChat = true, chat = it) }
                }
                setState { copy(isChat = false) }
                _callApi.value = response.message
                setState { copy(loading = false) }
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
                sendMessage(request)
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
        closeWebSocket()
    }

}
