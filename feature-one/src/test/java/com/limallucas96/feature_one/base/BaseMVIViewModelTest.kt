package com.limallucas96.feature_one.base

import app.cash.turbine.test
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState
import com.limallucas96.feature_one.dispatchers.TestDispatchers
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseMVIViewModelTest<
        UserAction : ViewAction,
        UIViewState : ViewState,
        UISideEffect : SideEffect,
        VM : BaseMVIViewModel<UserAction, UIViewState, UISideEffect>> :
    BaseViewModelDispatcher() {

    private lateinit var viewModel: VM

    abstract fun getViewModel(): VM

    @Before
    fun setupViewModel() {
        viewModel = getViewModel()
    }

    protected fun assertSideEffect(
        expectedSideEffect: UISideEffect,
        actions: List<UserAction>
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