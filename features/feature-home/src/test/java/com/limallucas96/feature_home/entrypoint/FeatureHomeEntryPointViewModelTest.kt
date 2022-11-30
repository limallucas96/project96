package com.limallucas96.feature_home.entrypoint

import com.example.analytics.analytics.Analytics
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class FeatureHomeEntryPointViewModelTest :
    BaseMVIViewModelTest<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect, FeatureHomeEntryPointViewModel>() {

    @Mock
    private lateinit var analytics: Analytics

    @Before
    fun setupViewModel() {
        viewModel = FeatureHomeEntryPointViewModel(analytics)
    }

    @Test
    fun `when OnCreate is dispatched, then assert navigate to home fragment is emitted`() = assertSideEffect(
        expectedSideEffect = FeatureHomeEntryPointSideEffect.NavigateToHomeFragment,
        actions = listOf(FeatureHomeEntryPointAction.OnCreate)
    )

    @Test
    fun `when OnCreate is dispatched, then assert that event is called`() {
        viewModel.dispatch(FeatureHomeEntryPointAction.OnCreate)
        verify(analytics).logFirebaseEvent("FEATURE_HOME_ENTRY_POINT_CREATION_EVENT")
    }



}