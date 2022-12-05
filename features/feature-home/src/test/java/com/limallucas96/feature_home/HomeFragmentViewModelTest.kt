package com.limallucas96.feature_home

import com.example.abtest.Abtest
import com.limallucas96.core_data.repositories.pet.PetRepository
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.domain_model.models.Cat
import com.limallucas96.feature_home.entrypoint.CoroutineTestRule
import com.limallucas96.feature_home.home.HomeFragmentAction
import com.limallucas96.feature_home.home.HomeFragmentSideEffect
import com.limallucas96.feature_home.home.HomeFragmentViewModel
import com.limallucas96.feature_home.home.HomeFragmentViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeFragmentViewModelTest :
    BaseMVIViewModelTest<HomeFragmentAction, HomeFragmentViewState, HomeFragmentSideEffect, HomeFragmentViewModel>() {

    @Mock
    private lateinit var abtest: Abtest

    @Mock
    private lateinit var petRepository: PetRepository

    @Mock
    private lateinit var resourcesProvider: ResourcesProvider

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setupViewModel() {
        viewModel =
            HomeFragmentViewModel(abtest, petRepository, resourcesProvider, coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    fun `Given a success getPets call and abtest showLastPet set to true, when view dispatches OnCreate action to viewModel, then assert view state`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = HomeFragmentViewState("pet counter: 1", "Last pet created: pet", true),
            actions = listOf(HomeFragmentAction.OnCreate),
            initializeMocks = {
                mockStrings()
                mockAbtest()
                mockSuccessfulCallOfGetPets()
            }
        )
    }

    @Test
    fun `when PrimaryButtonClick then assert NavigateToFeatureOne is emitted`() = assertSideEffect(
        expectedSideEffect = HomeFragmentSideEffect.NavigateToFeatureOne,
        actions = listOf(HomeFragmentAction.PrimaryButtonClick)
    )

    @Test
    fun `when SecondaryButtonClick then assert NavigateToFeatureTwo is emitted`() =
        assertSideEffect(
            expectedSideEffect = HomeFragmentSideEffect.NavigateToFeatureTwo,
            actions = listOf(HomeFragmentAction.SecondaryButtonClick)
        )

    private fun mockStrings() {
        `when`(resourcesProvider.getString(R.string.pet_counter, "1")).thenReturn("pet counter: 1")
        `when`(resourcesProvider.getString(R.string.last_pet_created, "pet")).thenReturn("Last pet created: pet")
    }

    private fun mockAbtest() {
        `when`(abtest.showLastPet()).thenReturn(true)
    }

    private suspend fun mockSuccessfulCallOfGetPets() {
        `when`(petRepository.getPets()).thenReturn(
            flowOf(
                Result.success(
                    listOf(
                        Cat(
                            name = "pet",
                            description = "",
                            url = ""
                        )
                    )
                )
            )
        )
    }

}