package com.limallucas96.feature_one.cat

import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.domain_model.models.Cat
import com.limallucas96.feature_one.base.BaseMVIViewModelTest
import com.limallucas96.feature_one.catpicker.CatPickerAction
import com.limallucas96.feature_one.catpicker.CatPickerSideEffect
import com.limallucas96.feature_one.catpicker.CatPickerViewModel
import com.limallucas96.feature_one.catpicker.CatPickerViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CatPickerViewModelTest :
    BaseMVIViewModelTest<CatPickerAction, CatPickerViewState, CatPickerSideEffect, CatPickerViewModel>() {

    private val mockCatPickerViewState = CatPickerViewState(
        isLoading = false,
        isError = false,
        catName = "",
        catAge = "",
        catUrlPhoto = ""
    )

    @Mock
    private lateinit var mockCatRepository: CatRepository

    override fun getViewModel(): CatPickerViewModel {
        return CatPickerViewModel(testDispatcher, mockCatRepository)
    }

    @Test
    fun `Given a success api call, when ViewScreen, ButtonShortNewCat or Retry are dispatched, then assert view is loading`() =
        assertViewState(
            expectedViewState = mockCatPickerViewState.copy(isLoading = true),
            actions = listOf(CatPickerAction.ViewScreen),
            initializeMocks = {
                mockSuccessGetCatsApiCall()
            }
        )

    @Test
    fun `Given a success api call, when ViewScreen, ButtonShortNewCat or Retry are dispatched, then assert view url`() =
        assertViewState(
            expectedViewState = mockCatPickerViewState.copy(
                isLoading = false,
                catUrlPhoto = "https",
            ),
            actions = listOf(CatPickerAction.ViewScreen),
            initializeMocks = {
                mockSuccessGetCatsApiCall()
            },
            emissionCount = 2
        )

    @Test
    fun `Given a success api call, when ViewScreen, ButtonShortNewCat or Retry are dispatched, then assert error`() =
        assertViewState(
            expectedViewState = mockCatPickerViewState.copy(
                isLoading = false,
                isError = true,
            ),
            actions = listOf(CatPickerAction.ViewScreen),
            initializeMocks = {
                mockErrorGetCatsApiCall()
            },
            emissionCount = 2
        )

    private fun mockSuccessGetCatsApiCall() = runTest {
        whenever(mockCatRepository.getCats()).then { listOf(Cat("cat", "description", "https")) }
    }

    private fun mockErrorGetCatsApiCall() = runTest {
        whenever(mockCatRepository.getCats()).then {throw Throwable() }
    }

}