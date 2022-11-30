package com.limallucas96.feature_one.entrypoint

import com.example.analytics.analytics.Analytics
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.verify

class FeatureOneEntryPointViewModelTest :
    BaseMVIViewModelTest<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect, FeatureOneEntryPointViewModel>() {

    @Mock
    private lateinit var analytics: Analytics

    @Before
    fun setupViewModel() {
        viewModel = FeatureOneEntryPointViewModel(analytics)
    }

    @Test
    fun `when OnCreate is dispatched, then assert right navigation is emitted`() = assertSideEffect(
        expectedSideEffect = FeatureOneEntryPointSideEffect.NavigateToCatProfileFragment(
            "FEATURE_ONE_ACTIVITY_BACK_STACK"
        ),
        actions = listOf(FeatureOneEntryPointAction.OnCreate)
    )

    @Test
    fun `when OnCreate is dispatched, then assert event is called`() {
        viewModel.dispatch(FeatureOneEntryPointAction.OnCreate)
        verify(analytics).logFirebaseEvent("FEATURE_ONE_ENTRY_POINT_CREATION_EVENT")
    }


}