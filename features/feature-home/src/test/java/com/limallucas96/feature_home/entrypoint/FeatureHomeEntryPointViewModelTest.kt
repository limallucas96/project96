package com.limallucas96.feature_home.entrypoint

import com.example.analytics.analytics.Analytics
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.feature_home.testrule.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeatureHomeEntryPointViewModelTest :
    BaseMVIViewModelTest<FeatureHomeEntryPointAction, FeatureHomeEntryPointViewState, FeatureHomeEntryPointSideEffect, FeatureHomeEntryPointViewModel>() {

    @MockK
    private lateinit var analytics: Analytics

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setupViewModel() {
        MockKAnnotations.init(this)
        viewModel = FeatureHomeEntryPointViewModel(
            analytics,
            coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `when OnCreate is dispatched, then assert navigate to home fragment is emitted`() = assertSideEffect(
        dispatcher = coroutinesTestRule.testDispatcherProvider,
        expectedSideEffect = FeatureHomeEntryPointSideEffect.NavigateToHomeFragment,
        actions = listOf(FeatureHomeEntryPointAction.OnCreate),
        initializeMocks = {
            every { analytics.logFirebaseEvent("FEATURE_HOME_ENTRY_POINT_CREATION_EVENT") } returns Unit
        }
    )

    @Test
    fun `when OnCreate is dispatched, then assert that event is called`() {
        viewModel.dispatch(FeatureHomeEntryPointAction.OnCreate)
        verify { analytics.logFirebaseEvent("FEATURE_HOME_ENTRY_POINT_CREATION_EVENT") }
    }

}