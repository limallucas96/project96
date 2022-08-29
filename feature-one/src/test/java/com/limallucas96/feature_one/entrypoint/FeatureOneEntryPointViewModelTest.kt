package com.limallucas96.feature_one.entrypoint

import com.limallucas96.feature_one.base.BaseMVIViewModelTest
import org.junit.Test

class FeatureOneEntryPointViewModelTest :
    BaseMVIViewModelTest<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect, FeatureOneEntryPointViewModel>() {

    override fun getViewModel(): FeatureOneEntryPointViewModel {
        return FeatureOneEntryPointViewModel()
    }

    @Test
    fun `when ViewScreen is dispatched, then assert right navigation is emitted`() = assertSideEffect(
        expectedSideEffect = FeatureOneEntryPointSideEffect.NavigateToFeatureOneFragment(
            "FEATURE_ONE_ACTIVITY_BACK_STACK"
        ),
        actions = listOf(FeatureOneEntryPointAction.ViewScreen)
    )

}