package com.limallucas96.mvi96.splash

import com.example.analytics.analytics.Analytics
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.mvi96.testrule.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest :
    BaseMVIViewModelTest<SplashAction, SplashViewState, SplashSideEffect, SplashViewModel>() {

    @MockK
    private lateinit var analytics: Analytics

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setupViewModel() {
        MockKAnnotations.init(this)
        viewModel = SplashViewModel(
            coroutinesTestRule.testDispatcherProvider,
            analytics
        )
    }

    @Test
    fun `when OnCreate is dispatched, then navigate to Home`() = assertSideEffect(
        dispatcher = coroutinesTestRule.testDispatcherProvider,
        expectedSideEffect = SplashSideEffect.NavigateToHome,
        actions = listOf(SplashAction.OnCreate),
        initializeMocks = {
            every { (analytics).logFirebaseEvent("SPLASH_CREATION_EVENT") } returns Unit
        }
    )

    @Test
    fun `when OnCreate is dispatched, then assert event is called`() {
        viewModel.dispatch(SplashAction.OnCreate)
        verify { (analytics).logFirebaseEvent("SPLASH_CREATION_EVENT") }
    }

}