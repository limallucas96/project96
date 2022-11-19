package com.limallucas96.feature_one.entrypoint

import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import org.junit.Before
import org.junit.Test

class FeatureOneEntryPointViewModelTest :
    BaseMVIViewModelTest<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect, FeatureOneEntryPointViewModel>() {

    @Before
    fun setupViewModel() {
        viewModel = FeatureOneEntryPointViewModel()
    }

    @Test
    fun `when ViewScreen is dispatched, then assert right navigation is emitted`() = assertSideEffect(
        expectedSideEffect = FeatureOneEntryPointSideEffect.NavigateToCatProfileFragment(
            "FEATURE_ONE_ACTIVITY_BACK_STACK"
        ),
        actions = listOf(FeatureOneEntryPointAction.OnCreate)
    )

}