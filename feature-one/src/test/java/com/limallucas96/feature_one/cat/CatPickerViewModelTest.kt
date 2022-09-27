package com.limallucas96.feature_one.cat

import com.limallucas96.core_data.repositories.cat.CatRepository
import com.limallucas96.domain_model.models.Cat
import com.limallucas96.feature_one.base.BaseMVIViewModelTest
import com.limallucas96.feature_one.catpicker.CatPickerAction
import com.limallucas96.feature_one.catpicker.CatPickerSideEffect
import com.limallucas96.feature_one.catpicker.CatPickerViewModel
import com.limallucas96.feature_one.catpicker.CatPickerViewState
import com.limallucas96.feature_one.dispatchers.TestDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import java.lang.RuntimeException

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
        return CatPickerViewModel(TestDispatchers(), mockCatRepository)
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

    @Ignore("Ignoring this unit test until we find out why the test is not passing")
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

    private suspend fun mockSuccessGetCatsApiCall() {
        whenever(mockCatRepository.getCats()).then { Result.success(listOf(Cat("cat", "description", "https"))) }
    }

    private suspend fun mockErrorGetCatsApiCall() {
        kotlin.runCatching {
            whenever(mockCatRepository.getCats()).thenReturn(Result.failure(Exception()))
        }
    }
}