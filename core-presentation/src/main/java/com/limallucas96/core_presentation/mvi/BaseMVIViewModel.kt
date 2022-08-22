package com.limallucas96.core_presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseMVIViewModel<UIEvent : ViewEvent, UIViewState : ViewState, SideEffect : SideEffect> :
    ViewModel() {

    private val initialState: UIViewState by lazy { createInitialState() }

    private val currentState: UIViewState get() = uiState.value

    private val _uiState: MutableStateFlow<UIViewState> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<UIEvent> = MutableSharedFlow()
    private val event = _event.asSharedFlow()

    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    abstract fun createInitialState(): UIViewState

    abstract fun handleEvent(event: UIEvent)

    protected fun setState(reduce: UIViewState.() -> UIViewState) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setSideEffect(effect: SideEffect) {
        val newSideEffect = effect
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