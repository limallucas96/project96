package com.limallucas96.mvi96.splash

import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest :
    BaseMVIViewModelTest<SplashAction, SplashViewState, SplashSideEffect, SplashViewModel>() {

    @Before
    fun setupViewModel() {
        viewModel = SplashViewModel()
    }

    @Test
    fun `when ViewScreen is dispatched, then navigate to Home`() = assertSideEffect(
        expectedSideEffect = SplashSideEffect.NavigateToHome,
        actions = listOf(SplashAction.OnCreate)
    )

}