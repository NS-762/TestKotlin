package com.example.testkotlin.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    private var viewModelJob = Job()

    open val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("Exception", "CoroutineExceptionHandler got $exception")
    }

    private val viewModelScope = CoroutineScope(IO + viewModelJob + exceptionHandler)
    private var isStarted = false

    fun start() {
        if (!isStarted) {
            isStarted = true
            onStart()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    protected abstract fun onStart()

    fun <P> doWorkInMainThread(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, Main)
    }

    fun <P> doWork(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, IO)
    }

    private inline fun <P> doCoroutineWork(
        crossinline doOnAsyncBlock: suspend CoroutineScope.() -> P,
        coroutineScope: CoroutineScope,
        context: CoroutineContext) {
        coroutineScope.launch {
            withContext(context) {
                doOnAsyncBlock.invoke(this)
            }
        }
    }
}
