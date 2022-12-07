//package com.limallucas96.feature_one.entrypoint
//
//import com.example.analytics.analytics.Analytics
//import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
//import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
//import com.limallucas96.feature_one.R
//import com.limallucas96.feature_one.enums.CatProfileProgress
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.kotlin.verify
//
//class FeatureOneEntryPointViewModelTest :
//    BaseMVIViewModelTest<FeatureOneEntryPointAction, FeatureOneEntryPointViewState, FeatureOneEntryPointSideEffect, FeatureOneEntryPointViewModel>() {
//
//    @Mock
//    private lateinit var analytics: Analytics
//
//    @Mock
//    private lateinit var resourcesProvider: ResourcesProvider
//
//    @Before
//    fun setupViewModel() {
//        viewModel = FeatureOneEntryPointViewModel(analytics, resourcesProvider)
//    }
//
//    @Test
//    fun `when OnCreate is dispatched, then assert right navigation is emitted`() = assertSideEffect(
//        expectedSideEffect = FeatureOneEntryPointSideEffect.NavigateToCatProfileFragment(
//            "FEATURE_ONE_ACTIVITY_BACK_STACK"
//        ),
//        actions = listOf(FeatureOneEntryPointAction.OnCreate)
//    )
//
//    @Test
//    fun `when OnCreate is dispatched, then assert event is called`() {
//        viewModel.dispatch(FeatureOneEntryPointAction.OnCreate)
//        verify(analytics).logFirebaseEvent("FEATURE_ONE_ENTRY_POINT_CREATION_EVENT")
//    }
//
//    @Test
//    fun `when UpdateToolbar is dispatched, then assert correct view state for each type`() {
//        mockStringResources()
//        CatProfileProgress.values().forEach { type ->
//            assertToolbarViewState(
//                type = type,
//                isProgressBarVisible = type != CatProfileProgress.NONE,
//                progressBarStep = type.step,
//                progressBarMax = CatProfileProgress.getSumOfSteps(),
//                isToolbarVisible = type != CatProfileProgress.NONE,
//                toolbarTitle = resourcesProvider.getString(type.stringRes)
//            )
//        }
//    }
//
//    private fun assertToolbarViewState(
//        type: CatProfileProgress,
//        isProgressBarVisible: Boolean,
//        progressBarStep: Int,
//        progressBarMax: Int,
//        isToolbarVisible: Boolean,
//        toolbarTitle: String,
//    ) {
//        assertViewState(
//            expectedViewState = FeatureOneEntryPointViewState(
//                isProgressBarVisible = isProgressBarVisible,
//                progressBarStep = progressBarStep,
//                progressBarMax = progressBarMax,
//                isToolbarVisible = isToolbarVisible,
//                toolbarTitle = toolbarTitle
//            ),
//            actions = listOf(FeatureOneEntryPointAction.UpdateToolbar(type)),
//            initializeMocks = {
//                mockStringResources() // TODO this is not initing correclty
//            }
//        )
//    }
//
//    private fun mockStringResources() {
//        Mockito.`when`(resourcesProvider.getString(R.string.cat_profile_step)).thenReturn("Cat Profile")
//        Mockito.`when`(resourcesProvider.getString(R.string.cat_picker_step)).thenReturn("Cat Picker")
//        Mockito.`when`(resourcesProvider.getString(R.string.cat_summary_step)).thenReturn("Cat Summary")
//        Mockito.`when`(resourcesProvider.getString(R.string.empty_string)).thenReturn("")
//    }
//
//}