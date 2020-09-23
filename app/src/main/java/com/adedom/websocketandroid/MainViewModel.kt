package com.adedom.websocketandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chat.ChatResponse
import com.chat.SendMessageRequest
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: DefaultChatRepository
) : BaseViewModel<MainState>(MainState()),
    WebSocketDruChat.SetOnWebSocketListener {

    private lateinit var webSocketDruChat: WebSocketDruChat
    private val _chat = MutableLiveData<List<ChatResponse>>()
    val chat: LiveData<List<ChatResponse>>
        get() = _chat

    private val _callApi = MutableLiveData<String>()
    val callApi: LiveData<String>
        get() = _callApi

    fun initialize() {
        launch {
            webSocketDruChat = WebSocketDruChat(this@MainViewModel).apply { initialize() }
        }
    }

    override fun onWebSocket(message: String) {
        val list = _chat.value as MutableList<ChatResponse>
        list.add(ChatResponse(message = message))
        _chat.value = list
    }

    override fun coroutineExceptionHandler() = initialize()

    fun fetchChat() {
        launch {
            try {
                setState { copy(loading = true) }
                val response = repository.fetchChat()
                _chat.value = response.chat
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
                val request = SendMessageRequest("Pathiphon", message)
                setState { copy(message = "", isSendMessage = true, loading = true) }
                setState { copy(isSendMessage = false) }
                val response = repository.sendMessage(request)
                _callApi.value = response.message
                setState { copy(loading = false) }
            } catch (e: Throwable) {
                setError(e)
                setState { copy(loading = true) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        webSocketDruChat.closeWebSocket()
    }

}
