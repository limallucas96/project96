package com.limallucas96.feature_one.cat

import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.feature_one.catprofile.CatProfileAction
import com.limallucas96.feature_one.catprofile.CatProfileSideEffect
import com.limallucas96.feature_one.catprofile.CatProfileViewModel
import com.limallucas96.feature_one.catprofile.CatProfileViewState
import com.limallucas96.feature_one.testrule.CoroutineTestRule
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
class CatProfileViewModelTest :
    BaseMVIViewModelTest<CatProfileAction, CatProfileViewState, CatProfileSideEffect, CatProfileViewModel>() {

    private val mockCatProfileViewState = CatProfileViewState(
        catName = "",
        catAge = "",
        isContinueButtonEnable = false
    )

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setupViewModel() {
        viewModel = CatProfileViewModel(coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun `Given only the cat name, when OnCatNameChanged is dispatched, then assert that isContinueButtonEnable is false`() =
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = mockCatProfileViewState.copy(
                catName = "Cat",
                catAge = "",
                isContinueButtonEnable = false
            ),
            actions = listOf(CatProfileAction.OnCatNameChanged(catName = "Cat"))
        )

    @Test
    fun `Given only the cat age, when OnCatAgeChanged is dispatched, then assert that isContinueButtonEnable is false`() =
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = mockCatProfileViewState.copy(
                catName = "",
                catAge = "2",
                isContinueButtonEnable = false
            ),
            actions = listOf(CatProfileAction.OnCatAgeChanged(catAge = "2"))
        )

    @Test
    fun `Given both cat name and cat age, When OnCatNameChanged or OnCatAgeChanged, then assert that isContinueButtonEnable is true`() =
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = mockCatProfileViewState.copy(
                catName = "Cat",
                catAge = "2",
                isContinueButtonEnable = true
            ),
            actions = listOf(
                CatProfileAction.OnCatNameChanged(catName = "Cat"),
                CatProfileAction.OnCatAgeChanged(catAge = "2")
            ),
            emissionCount = 2
        )

    @Test
    fun `Given cat name and cat age, when ButtonContinueClick is dispatched, then assert that NavigateToCatPicker is emitted`() =
        assertSideEffect(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedSideEffect = CatProfileSideEffect.NavigateToCatPicker(
                catName = "Cat",
                catAge = "2",
                backStack = "CAT_PROFILE_BACK_STACK"
            ),
            actions = listOf(
                CatProfileAction.OnCatNameChanged(catName = "Cat"),
                CatProfileAction.OnCatAgeChanged(catAge = "2"),
                CatProfileAction.ButtonContinueClick
            )
        )

}