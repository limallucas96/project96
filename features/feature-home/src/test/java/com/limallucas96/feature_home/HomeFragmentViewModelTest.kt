package com.limallucas96.feature_home

import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.feature_home.home.HomeFragmentAction
import com.limallucas96.feature_home.home.HomeFragmentSideEffect
import com.limallucas96.feature_home.home.HomeFragmentViewModel
import com.limallucas96.feature_home.home.HomeFragmentViewState
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeFragmentViewModelTest :
    BaseMVIViewModelTest<HomeFragmentAction, HomeFragmentViewState, HomeFragmentSideEffect, HomeFragmentViewModel>() {

    @Mock
    private lateinit var resourcesProvider: ResourcesProvider

    @Before
    fun setupViewModel() {
        viewModel = HomeFragmentViewModel(resourcesProvider)
    }

    @Ignore("Check about error Test worker @coroutine#5")
    @Test
    fun `when ViewScreen is dispatched, then assert correct view state`() =
        assertViewState(
            expectedViewState = HomeFragmentViewState("pet counter: 10"),
            actions = listOf(HomeFragmentAction.OnCreate),
            initializeMocks = { mockStrings() }
        )

    @Test
    fun `when PrimaryButtonClick then assert NavigateToFeatureOne is emitted`() = assertSideEffect(
        expectedSideEffect = HomeFragmentSideEffect.NavigateToFeatureOne,
        actions = listOf(HomeFragmentAction.PrimaryButtonClick)
    )

    @Test
    fun `when SecondaryButtonClick then assert NavigateToFeatureTwo is emitted`() =
        assertSideEffect(
            expectedSideEffect = HomeFragmentSideEffect.NavigateToFeatureTwo,
            actions = listOf(HomeFragmentAction.SecondaryButtonClick)
        )

    private fun mockStrings() {
        `when`(resourcesProvider.getString(R.string.pet_counter)).thenReturn("pet counter: 10")
    }
}