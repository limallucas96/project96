package com.limallucas96.core_presentation_test.base

import app.cash.turbine.test
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseMVIViewModelTest<
        UserAction : ViewAction,
        UIViewState : ViewState,
        UISideEffect : SideEffect,
        VM : BaseMVIViewModel<UserAction, UIViewState, UISideEffect>> :
    BaseViewModelDispatcher() {

    protected lateinit var viewModel: VM

    protected fun assertSideEffect(
        expectedSideEffect: UISideEffect,
        actions: List<UserAction>,
        initializeMocks: suspend () -> Unit = {}
    ) = runTest(UnconfinedTestDispatcher()) {

        // Dispatch actions
        actions.forEach { userAction ->
            viewModel.dispatch(userAction)
        }

        // Asserts right side effect was emitted when the action was dispatched
        viewModel.sideEffect.test {
            assertEquals(expectedSideEffect, awaitItem())
            ensureAllEventsConsumed()
        }
    }

    protected fun assertViewState(
        expectedViewState: UIViewState,
        actions: List<UserAction>,
        initializeMocks: suspend () -> Unit = {},
        emissionCount: Int = 1,
    ) = runTest(UnconfinedTestDispatcher()) {

        // Init mocks
        initializeMocks()
        advanceUntilIdle()

        // Collects all emitted view states
        val viewStateList = mutableListOf<UIViewState>()
        val job = launch { viewModel.viewState.toList(viewStateList) }

        // Dispatch actions
        actions.forEach { userAction -> viewModel.dispatch(userAction) }

        // Asserts view state at specific index with the expected view state
        assertEquals(viewStateList.getOrNull(emissionCount), expectedViewState)

        // Cancels job
        job.cancel()
    }

}