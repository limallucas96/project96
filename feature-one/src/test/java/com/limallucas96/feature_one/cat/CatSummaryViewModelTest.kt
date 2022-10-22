//package com.limallucas96.feature_one.cat
//
//import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
//import com.limallucas96.feature_one.catsummary.CatSummaryAction
//import com.limallucas96.feature_one.catsummary.CatSummarySideEffect
//import com.limallucas96.feature_one.catsummary.CatSummaryViewModel
//import com.limallucas96.feature_one.catsummary.CatSummaryViewState
//import org.junit.Before
//import org.junit.Test
//
//class CatSummaryViewModelTest :
//    BaseMVIViewModelTest<CatSummaryAction, CatSummaryViewState, CatSummarySideEffect, CatSummaryViewModel>() {
//
//    private val mockCatSummaryViewState = CatSummaryViewState(
//        catSummary = "",
//        catPhotoUrl = ""
//    )
//
//    @Before
//    fun setupViewModel() {
//        viewModel = CatSummaryViewModel()
//    }
//
//    @Test
//    fun `when ButtonGoToHomeClick is dispatched, then assert that CatSummarySideEffect is emitted`() {
//        assertSideEffect(
//            expectedSideEffect = CatSummarySideEffect.NavigateToHome,
//            actions = listOf(CatSummaryAction.ButtonGoToHomeClick)
//        )
//    }
//
//    @Test
//    fun `when ButtonGoToHomeClick is dispatched then assert that NavigateToCatProfile is emitted`() {
//        assertSideEffect(
//            expectedSideEffect = CatSummarySideEffect.NavigateToCatProfile(
//                clearBackStack = true
//            ),
//            actions = listOf(CatSummaryAction.ButtonGoToCatProfileClick)
//        )
//    }
//
//    @Test
//    fun `when ViewScreen is dispatched, then assert view state`() {
//        assertViewState(
//            expectedViewState = mockCatSummaryViewState.copy(
//                catSummary = "Cat\n1\n",
//                catPhotoUrl = "https"
//            ),
//            actions = listOf(
//                CatSummaryAction.ViewScreen(
//                    catName = "Cat",
//                    catAge = "1",
//                    catPhotoUrl = "https"
//                )
//            )
//        )
//    }
//
//}