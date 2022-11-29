package com.limallucas96.mvi96.splash

import com.example.analytics.analytics.Analytics
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest :
    BaseMVIViewModelTest<SplashAction, SplashViewState, SplashSideEffect, SplashViewModel>() {

    @Mock
    private lateinit var analytics: Analytics

    @Before
    fun setupViewModel() {
        viewModel = SplashViewModel(analytics)
    }

    @Test
    fun `when OnCreate is dispatched, then navigate to Home`() = assertSideEffect(
        expectedSideEffect = SplashSideEffect.NavigateToHome,
        actions = listOf(SplashAction.OnCreate)
    )

}