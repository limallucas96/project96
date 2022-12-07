package com.limallucas96.feature_home.home

import com.example.abtest.Abtest
import com.limallucas96.core_data.repositories.pet.PetRepository
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.domain_model.models.Cat
import com.limallucas96.feature_home.R
import com.limallucas96.feature_home.testrule.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
        viewModel = HomeFragmentViewModel(
            abtest,
            petRepository,
            resourcesProvider,
            coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `Given a success getPets call and abtest showLastPet set to true, when view dispatches OnCreate action to viewModel, then assert view state`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = HomeFragmentViewState("pet counter: 1", "Last pet created: pet", true),
            actions = listOf(HomeFragmentAction.OnCreate),
            initializeMocks = {
                mockStrings()
                mockAbtest(true)
                mockSuccessfulCallOfGetPets()
            }
        )
    }

    @Test
    fun `Given a success getPets call and abtest showLastPet set to false, when view dispatches OnCreate action to viewModel, then assert view state`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = HomeFragmentViewState("pet counter: 1", "Last pet created: pet", false),
            actions = listOf(HomeFragmentAction.OnCreate),
            initializeMocks = {
                mockStrings()
                mockAbtest(false)
                mockSuccessfulCallOfGetPets()
            }
        )
    }

    @Test
    fun `Given a fail getPets call, when view dispatches OnCreate action to viewModel, then assert view state`() {
        tempAssertViewState(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedViewState = HomeFragmentViewState(
                petCounter = "We could not retrieve any pet from our database",
                showLastPet = false
            ),
            actions = listOf(HomeFragmentAction.OnCreate),
            initializeMocks = {
                mockStrings()
                mockFailureCallOfGetPets()
            }
        )
    }


    @Test
    fun `when PrimaryButtonClick then assert NavigateToFeatureOne is emitted`() = assertSideEffect(
        dispatcher = coroutinesTestRule.testDispatcherProvider,
        expectedSideEffect = HomeFragmentSideEffect.NavigateToFeatureOne,
        actions = listOf(HomeFragmentAction.PrimaryButtonClick)
    )

    @Test
    fun `when SecondaryButtonClick then assert NavigateToFeatureTwo is emitted`() =
        assertSideEffect(
            dispatcher = coroutinesTestRule.testDispatcherProvider,
            expectedSideEffect = HomeFragmentSideEffect.NavigateToFeatureTwo,
            actions = listOf(HomeFragmentAction.SecondaryButtonClick)
        )

    private fun mockStrings() {
        `when`(resourcesProvider.getString(R.string.pet_counter, "1")).thenReturn("pet counter: 1")
        `when`(resourcesProvider.getString(R.string.last_pet_created, "pet")).thenReturn("Last pet created: pet")
        `when`(resourcesProvider.getString(R.string.pet_counter_error)).thenReturn("We could not retrieve any pet from our database")
    }

    private fun mockAbtest(value: Boolean) {
        `when`(abtest.showLastPet()).thenReturn(value)
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

    private suspend fun mockFailureCallOfGetPets() {
        `when`(petRepository.getPets()).thenReturn(flowOf(Result.failure(Exception())))
    }

}