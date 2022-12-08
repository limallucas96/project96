package com.limallucas96.feature_one.entrypoint

import com.example.analytics.analytics.Analytics
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.feature_one.R
import com.limallucas96.feature_one.enums.CatProfileProgress
import com.limallucas96.feature_one.testrule.CoroutineTestRule
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeatureOneEntryPointViewModelTest :
    BaseMVIViewModelTest<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect, FeatureOneEntryPointViewModel>() {

    @MockK
    private lateinit var analytics: Analytics

    @RelaxedMockK
    private lateinit var resourcesProvider: ResourcesProvider

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setupViewModel() {
        viewModel = FeatureOneEntryPointViewModel(
            coroutinesTestRule.testDispatcherProvider,
            analytics,
            resourcesProvider
        )
    }

    @Test
    fun `when OnCreate is dispatched, then assert right navigation is emitted`() = assertSideEffect(
        dispatcher = coroutinesTestRule.testDispatcherProvider,
        expectedSideEffect = FeatureOneEntryPointSideEffect.NavigateToCatProfileFragment(
            "FEATURE_ONE_ACTIVITY_BACK_STACK"
        ),
        actions = listOf(FeatureOneEntryPointAction.OnCreate)
    )

    @Test
    fun `when OnCreate is dispatched, then assert event is called`() {
        viewModel.dispatch(FeatureOneEntryPointAction.OnCreate)
        verify { analytics.logFirebaseEvent("FEATURE_ONE_ENTRY_POINT_CREATION_EVENT") }
    }

    @Test
    fun `when UpdateToolbar is dispatched, then assert correct view state for each type`() {
        CatProfileProgress.values().forEach { type ->
            assertToolbarViewState(
                type = type,
                isProgressBarVisible = type != CatProfileProgress.NONE,
                progressBarStep = type.step,
                progressBarMax = CatProfileProgress.getSumOfSteps(),
                isToolbarVisible = type != CatProfileProgress.NONE
            )
        }
    }

    private fun assertToolbarViewState(
        type: CatProfileProgress,
        isProgressBarVisible: Boolean,
        progressBarStep: Int,
        progressBarMax: Int,
        isToolbarVisible: Boolean
    ) {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = FeatureOneEntryPointViewState(
                isProgressBarVisible = isProgressBarVisible,
                progressBarStep = progressBarStep,
                progressBarMax = progressBarMax,
                isToolbarVisible = isToolbarVisible,
                toolbarTitle = getToolbarTitle(type)
            ),
            actions = listOf(FeatureOneEntryPointAction.UpdateToolbar(type)),
            initializeMocks = { mockStringResources() }
        )
    }

    private fun getToolbarTitle(type: CatProfileProgress): String {
        return when (type) {
            CatProfileProgress.CAT_PROFILE_STEP -> "Cat Profile"
            CatProfileProgress.CAT_PICKER_STEP -> "Cat Picker"
            CatProfileProgress.CAT_SUMMARY_STEP -> "Cat Summary"
            CatProfileProgress.NONE -> ""
        }
    }

    private fun mockStringResources() {
        every { resourcesProvider.getString(R.string.cat_profile_step) } returns "Cat Profile"
        every { resourcesProvider.getString(R.string.cat_picker_step) } returns "Cat Picker"
        every { resourcesProvider.getString(R.string.cat_summary_step) } returns "Cat Summary"
        every { resourcesProvider.getString(R.string.empty_string) } returns ""
    }
}