package com.limallucas96.core_presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseMVIViewModel<UIEvent : ViewEvent, UIViewState : ViewState, UISideEffect : SideEffect> :
    ViewModel() {

    private val initialViewState: UIViewState by lazy { createInitialViewState() }

    private val currentState: UIViewState get() = viewState.value

    private val _viewState: MutableStateFlow<UIViewState> = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    private val _event: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    private val event = _event.asSharedFlow()

    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    abstract fun createInitialViewState(): UIViewState

    abstract fun handleEvent(event: UIEvent)

    protected fun setState(reduce: UIViewState.() -> UIViewState) {
        val newState = currentState.reduce()
        _viewState.value = newState
    }

    protected fun setSideEffect(sideEffect: SideEffect) {
        val newSideEffect = sideEffect
        viewModelScope.launch { _sideEffect.send(newSideEffect) }
    }

    private fun subscribeEvents() {
        viewModelScope.launch { event.collect { handleEvent(it) } }
    }

    fun setEvent(event: UIEvent) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

}