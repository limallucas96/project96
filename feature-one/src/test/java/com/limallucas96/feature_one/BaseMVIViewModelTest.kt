package com.limallucas96.feature_one

import app.cash.turbine.test
import com.limallucas96.core_presentation.mvi.BaseMVIViewModel
import com.limallucas96.core_presentation.mvi.SideEffect
import com.limallucas96.core_presentation.mvi.ViewAction
import com.limallucas96.core_presentation.mvi.ViewState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before

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
    ) = runTest {

        actions.forEach { userAction ->
            viewModel.dispatch(userAction)
        }

        viewModel.sideEffect.test {
            assertEquals(expectedSideEffect, awaitItem())
        }
    }

    protected fun assertViewState(
        expectedViewState: UIViewState,
        actions: List<UserAction>
    ) = runTest {

        actions.forEach { userAction ->
            viewModel.dispatch(userAction)
        }

        assertEquals(viewModel.viewState.value, expectedViewState)
    }

}