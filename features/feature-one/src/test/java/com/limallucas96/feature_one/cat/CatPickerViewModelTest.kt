package com.limallucas96.feature_one.cat

import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.domain_model.models.Cat
import com.limallucas96.feature_one.catpicker.CatPickerAction
import com.limallucas96.feature_one.catpicker.CatPickerSideEffect
import com.limallucas96.feature_one.catpicker.CatPickerViewModel
import com.limallucas96.feature_one.catpicker.CatPickerViewState
import com.limallucas96.feature_one.testrule.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
class CatPickerViewModelTest :
    BaseMVIViewModelTest<CatPickerAction, CatPickerViewState, CatPickerSideEffect, CatPickerViewModel>() {

    private val testViewState = CatPickerViewState(
        isLoading = false,
        isError = false,
        catName = "",
        catAge = "",
        catUrlPhoto = ""
    )

    @MockK
    private lateinit var catRepository: CatRepository

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setupViewModel() {
        viewModel = CatPickerViewModel(
            coroutinesTestRule.testDispatcherProvider,
            catRepository
        )
    }

    @Test
    fun `Given a success api call to get cats, when ButtonShortNewCat or Retry are dispatched, then assert view state is loading`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = testViewState.copy(isLoading = true),
            actions = listOf(CatPickerAction.ButtonShortNewCat),
            initializeMocks = { coEvery { catRepository.getCats() } returns Result.success(listOf()) },
            emissionCount = 1
        )
    }

    @Test
    fun `Given a success api call to get cats, when ButtonShortNewCat or Retry are dispatched, then assert view state is not loading and has a cat url`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = testViewState.copy(catUrlPhoto = "url"),
            actions = listOf(CatPickerAction.ButtonShortNewCat),
            initializeMocks = { coEvery { catRepository.getCats() } returns Result.success(listOf(Cat(name = "name", description = "description", url = "url"))) },
            emissionCount = 2
        )
    }

    @Test
    fun `Given a failure api call to get cats, when ButtonShortNewCat or Retry are dispatched, then assert view state is error set to true`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = testViewState.copy(isError = true),
            actions = listOf(CatPickerAction.ButtonShortNewCat),
            initializeMocks = { coEvery { catRepository.getCats() }  returns Result.failure(Exception()) },
            emissionCount = 2
        )
    }

    @Test
    fun `Given a success api call to get cats, when OnCreate is dispatched, then assert view state is loading set to true`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = testViewState.copy(isLoading = true, catName = "name", catAge = "123"),
            actions = listOf(CatPickerAction.OnCreate(catName = "name", catAge = "123")),
            initializeMocks = { coEvery { catRepository.getCats() } returns Result.success(listOf(Cat(name = "name", description = "description", url = "url"))) },
            emissionCount = 1
        )
    }

    @Test
    fun `Given a success api call to get cats, when OnCreate is dispatched, then assert view state have all expected values`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = testViewState.copy(catName = "name", catAge = "123", catUrlPhoto = "url"),
            actions = listOf(CatPickerAction.OnCreate(catName = "name", catAge = "123")),
            initializeMocks = { coEvery { catRepository.getCats() } returns Result.success(listOf(Cat(name = "name", description = "description", url = "url"))) },
            emissionCount = 2
        )
    }

    @Test
    fun `Given a failure api call to get cats, when OnCreate is dispatched, then assert view state have all expected values`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = testViewState.copy(isError = true, catName = "name", catAge = "123"),
            actions = listOf(CatPickerAction.OnCreate(catName = "name", catAge = "123")),
            initializeMocks = { coEvery { catRepository.getCats() }  returns Result.failure(Exception()) },
            emissionCount = 2
        )
    }

    @Test
    fun `When ButtonChooseCatClick is dispatched, then assert NavigateToCatSummary is emitted`() {
        assertSideEffect(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedSideEffect = CatPickerSideEffect.NavigateToCatSummary("name", "123", "url" , true),
            actions = listOf(
                CatPickerAction.OnCreate(catName = "name", catAge = "123"),
                CatPickerAction.ButtonChooseCatClick
            ),
            initializeMocks = { coEvery { catRepository.getCats() } returns Result.success(listOf(Cat(name = "name", description = "description", url = "url"))) }
        )
    }

}