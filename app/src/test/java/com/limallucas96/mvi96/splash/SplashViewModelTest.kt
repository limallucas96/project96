package com.limallucas96.mvi96.splash

import com.example.analytics.analytics.Analytics
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.mvi96.testrule.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest :
    BaseMVIViewModelTest<SplashAction, SplashViewState, SplashSideEffect, SplashViewModel>() {

    @Mock
    private lateinit var analytics: Analytics

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setupViewModel() {
        viewModel = SplashViewModel(
            analytics,
            coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `when OnCreate is dispatched, then navigate to Home`() = assertSideEffect(
        dispatcher = coroutinesTestRule.testDispatcherProvider,
        expectedSideEffect = SplashSideEffect.NavigateToHome,
        actions = listOf(SplashAction.OnCreate)
    )

    @Test
    fun `when OnCreate is dispatched, then assert event is called`() {
        viewModel.dispatch(SplashAction.OnCreate)
        verify(analytics).logFirebaseEvent("SPLASH_CREATION_EVENT")
    }

}