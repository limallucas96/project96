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
        actions = listOf(SplashAction.ViewScreen)
    )

//    @Before
//    fun setupViewModel() {
//        viewModel = SplashViewModel(appSharedPreferences, resourcesProvider)
//    }
//
//    @Ignore("Check about error Test worker @coroutine#5")
//    @Test
//    fun `when ViewScreen is dispatched, then assert correct view state`() =
//        assertViewState(
//            expectedViewState = SplashViewState("pet counter: 10"),
//            actions = listOf(SplashAction.ViewScreen),
//            initializeMocks = { mockStrings() }
//        )
//
//    @Test
//    fun `when PrimaryButtonClick then assert NavigateToFeatureOne is emitted`() = assertSideEffect(
//        expectedSideEffect = SplashSideEffect.NavigateToFeatureOne,
//        actions = listOf(SplashAction.PrimaryButtonClick)
//    )
//
//    @Test
//    fun `when SecondaryButtonClick then assert NavigateToFeatureTwo is emitted`() =
//        assertSideEffect(
//            expectedSideEffect = SplashSideEffect.NavigateToFeatureTwo,
//            actions = listOf(SplashAction.SecondaryButtonClick)
//        )
//
//    private fun mockStrings() {
//        `when`(resourcesProvider.getString(R.string.pet_counter)).thenReturn("pet counter: 10")
//    }

}