package com.adedom.websocketandroid.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<S : Any>(private val initialState: S) : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val exceptionHandler = CoroutineExceptionHandler { _, err ->
        coroutineExceptionHandler()
        if (!err.message.isNullOrBlank()) {
            setError(err)
        }
    }
    private val _state = MutableLiveData<S>().apply { value = initialState }
    private val _error = MutableLiveData<Throwable>()
    private val _attachFirstTime = MutableLiveData<Unit>().apply { value = Unit }

    val state: LiveData<S> = _state
    val error: LiveData<Throwable> = _error
    val attachFirstTime: LiveData<Unit> = _attachFirstTime

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + exceptionHandler

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }

    open fun coroutineExceptionHandler() {}

    protected fun setState(reducer: S.() -> S) {
        _state.value = (_state.value ?: initialState).reducer()
    }

    protected fun setError(throwable: Throwable) {
        _error.value = throwable
    }

}
