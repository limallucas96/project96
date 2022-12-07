//package com.limallucas96.feature_one.cat
//
//import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
//import com.limallucas96.feature_one.catprofile.CatProfileAction
//import com.limallucas96.feature_one.catprofile.CatProfileSideEffect
//import com.limallucas96.feature_one.catprofile.CatProfileViewModel
//import com.limallucas96.feature_one.catprofile.CatProfileViewState
//import org.junit.Before
//import org.junit.Test
//
//class CatProfileViewModelTest :
//    BaseMVIViewModelTest<CatProfileAction, CatProfileViewState, CatProfileSideEffect, CatProfileViewModel>() {
//
//    private val mockCatProfileViewState = CatProfileViewState(
//        catName = "",
//        catAge = "",
//        isContinueButtonEnable = false
//    )
//
//    @Before
//    fun setupViewModel() {
//        viewModel = CatProfileViewModel()
//    }
//
//    @Test
//    fun `When only cat name is populated, then assert that isContinueButtonEnable is false`() =
//        assertViewState(
//            expectedViewState = mockCatProfileViewState.copy(
//                catName = "Cat",
//                catAge = "",
//                isContinueButtonEnable = false
//            ),
//            actions = listOf(CatProfileAction.OnCatNameChanged(catName = "Cat"))
//        )
//
//    @Test
//    fun `When only cat age is populated, then assert that isContinueButtonEnable is false`() =
//        assertViewState(
//            expectedViewState = mockCatProfileViewState.copy(
//                catName = "",
//                catAge = "2",
//                isContinueButtonEnable = false
//            ),
//            actions = listOf(CatProfileAction.OnCatAgeChanged(catAge = "2"))
//        )
//
//    @Test
//    fun `When both cat name and age are populated, then assert that isContinueButtonEnable is true`() =
//        assertViewState(
//            expectedViewState = mockCatProfileViewState.copy(
//                catName = "Cat",
//                catAge = "2",
//                isContinueButtonEnable = true
//            ),
//            actions = listOf(
//                CatProfileAction.OnCatNameChanged(catName = "Cat"),
//                CatProfileAction.OnCatAgeChanged(catAge = "2")
//            ),
//            emissionCount = 2
//        )
//
//    @Test
//    fun `When ButtonContinueClick is dispatched, then assert that NavigateToCatPicker is emitted`() =
//        assertSideEffect(
//            expectedSideEffect = CatProfileSideEffect.NavigateToCatPicker(
//                catName = "",
//                catAge = "",
//                backStack = "CAT_PROFILE_BACK_STACK"
//            ),
//            actions = listOf(CatProfileAction.ButtonContinueClick)
//        )
//
//}