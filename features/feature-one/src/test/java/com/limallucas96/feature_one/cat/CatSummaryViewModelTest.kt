package com.limallucas96.feature_one.cat

import com.limallucas96.core_data.repositories.pet.PetRepository
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.feature_one.R
import com.limallucas96.feature_one.catsummary.CatSummaryAction
import com.limallucas96.feature_one.catsummary.CatSummarySideEffect
import com.limallucas96.feature_one.catsummary.CatSummaryViewModel
import com.limallucas96.feature_one.catsummary.CatSummaryViewState
import com.limallucas96.feature_one.testrule.CoroutineTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatSummaryViewModelTest :
    BaseMVIViewModelTest<CatSummaryAction, CatSummaryViewState, CatSummarySideEffect, CatSummaryViewModel>() {

    private val mockCatSummaryViewState = CatSummaryViewState(
        catName = "",
        catAge = "",
        catPhotoUrl = ""
    )

    @MockK(relaxUnitFun = true, relaxed = true)
    private lateinit var petRepository: PetRepository

    @RelaxedMockK
    private lateinit var resourcesProvider: ResourcesProvider

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setupViewModel() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CatSummaryViewModel(
            coroutinesTestRule.testDispatcherProvider,
            resourcesProvider,
            petRepository
        )
    }

    @Test
    fun `Given cat name, cat age and cat url, when OnCreate is dispatched, then assert view state correct value`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = mockCatSummaryViewState.copy(
                catName = "My cat name: name",
                catAge = "My cat age: 123",
                catPhotoUrl = "url"
            ),
            actions = listOf(CatSummaryAction.OnCreate(catName = "name", catAge = "123", catPhotoUrl = "url")),
            initializeMocks = { mockResourceProvider() }
        )
    }

    @Test
    fun `Given an user action, when ButtonGoToHomeClick is dispatched, then assert NavigateToHome is emitted`() {
        assertSideEffect(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedSideEffect = CatSummarySideEffect.NavigateToHome,
            actions = listOf(CatSummaryAction.ButtonGoToHomeClick)
        )
    }

    @Test
    fun `Given an user action, when ButtonGoToCatProfileClick is dispatched, then assert NavigateToCatProfile is emitted`() {
        assertSideEffect(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedSideEffect = CatSummarySideEffect.NavigateToCatProfile(true),
            actions = listOf(CatSummaryAction.ButtonGoToCatProfileClick)
        )
    }

    @Test
    fun `Given an user action, when DialogConfirmExitClick is dispatched, then assert NavigateToHome is emitted`() {
        assertSideEffect(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedSideEffect = CatSummarySideEffect.NavigateToHome,
            actions = listOf(CatSummaryAction.DialogConfirmExitClick)
        )
    }

    @Test
    fun `Given a success init of the view model, when ButtonSaveLocallyClick is dispatched, then assert ShowExitConfirmationDialog is emitted`() {
        assertSideEffect(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedSideEffect = CatSummarySideEffect.ShowExitConfirmationDialog,
            actions = listOf(
                CatSummaryAction.OnCreate(catName = "My cat name: name", catAge = "My cat age: 123", catPhotoUrl = "url"),
                CatSummaryAction.ButtonSaveLocallyClick
            ),
            initializeMocks = {
                mockResourceProvider()
                mockInsertPets()
            }
        )
    }

    private fun mockResourceProvider() {
        every { resourcesProvider.getString(R.string.my_cat_name, "name") } answers { "My cat name: name" }
        every { resourcesProvider.getString(R.string.my_cat_age, "123") } answers { "My cat age: 123" }
    }

    private fun mockInsertPets() {
        coVerify { petRepository.insertPet("My cat name: name", 0, "url") }
    }

}