package com.limallucas96.feature_home.entrypoint

import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeatureHomeEntryPointViewModelTest :
    BaseMVIViewModelTest<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect, FeatureHomeEntryPointViewModel>() {

    @Before
    fun setupViewModel() {
        viewModel = FeatureHomeEntryPointViewModel()
    }

    @Test
    fun `when ViewScreen is dispatched, then assert navigate to home fragment is emitted`() = assertSideEffect(
        expectedSideEffect = FeatureHomeEntryPointSideEffect.NavigateToHomeFragment,
        actions = listOf(FeatureHomeEntryPointAction.OnCreate)
    )

}