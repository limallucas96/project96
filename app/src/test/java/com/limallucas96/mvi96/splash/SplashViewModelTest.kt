package com.limallucas96.mvi96.splash

import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.core_sharedpreferences.sharedpreferences.AppSharedPreferences
import com.limallucas96.mvi96.R
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest :
    BaseMVIViewModelTest<SplashAction, SplashViewState, SplashSideEffect, SplashViewModel>() {

    @Mock
    private lateinit var appSharedPreferences: AppSharedPreferences

    @Mock
    private lateinit var resourcesProvider: ResourcesProvider


    @Before
    fun setupViewModel() {
        viewModel = SplashViewModel(appSharedPreferences, resourcesProvider)
    }

    @Ignore("Check about error Test worker @coroutine#5")
    @Test
    fun `when ViewScreen is dispatched, then assert correct view state`() =
        assertViewState(
            expectedViewState = SplashViewState("pet counter: 10"),
            actions = listOf(SplashAction.ViewScreen),
            initializeMocks = { mockStrings() }
        )

    @Test
    fun `when PrimaryButtonClick then assert NavigateToFeatureOne is emitted`() = assertSideEffect(
        expectedSideEffect = SplashSideEffect.NavigateToFeatureOne,
        actions = listOf(SplashAction.PrimaryButtonClick)
    )

    @Test
    fun `when SecondaryButtonClick then assert NavigateToFeatureTwo is emitted`() =
        assertSideEffect(
            expectedSideEffect = SplashSideEffect.NavigateToFeatureTwo,
            actions = listOf(SplashAction.SecondaryButtonClick)
        )

    private fun mockStrings() {
        `when`(resourcesProvider.getString(R.string.pet_counter)).thenReturn("pet counter: 10")
    }

}