package com.limallucas96.core_presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseMVIViewModel<UserAction : ViewAction, UIViewState : ViewState, UISideEffect : SideEffect> :
    ViewModel() {

    private val initialViewState: UIViewState by lazy { createInitialViewState() }

    private val currentState: UIViewState get() = viewState.value

    private val _viewState: MutableStateFlow<UIViewState> = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    private val _action: MutableSharedFlow<UserAction> = MutableSharedFlow()
    private val action = _action.asSharedFlow()

    private val _sideEffect: Channel<UISideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        observeActions()
    }

    abstract fun createInitialViewState(): UIViewState

    abstract fun handleUserAction(action: UserAction, currentState: UIViewState)

    protected fun updateViewState(reduce: UIViewState.() -> UIViewState) {
        val newState = currentState.reduce()
        _viewState.value = newState
    }

    protected fun emitSideEffect(sideEffect: UISideEffect) {
        val newSideEffect = sideEffect
        viewModelScope.launch { _sideEffect.send(newSideEffect) }
    }

    private fun observeActions() {
        viewModelScope.launch { action.collect { handleUserAction(it, currentState) } }
    }

    fun dispatch(action: UserAction) {
        viewModelScope.launch { _action.emit(action) }
    }

}