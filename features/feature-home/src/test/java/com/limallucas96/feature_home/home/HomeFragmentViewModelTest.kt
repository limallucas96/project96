package com.limallucas96.feature_home.home

import com.example.abtest.Abtest
import com.limallucas96.core_data.repositories.pet.PetRepository
import com.limallucas96.core_presentation.resourceprovider.ResourcesProvider
import com.limallucas96.core_presentation_test.base.BaseMVIViewModelTest
import com.limallucas96.domain_model.models.Cat
import com.limallucas96.feature_home.R
import com.limallucas96.feature_home.testrule.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeFragmentViewModelTest :
    BaseMVIViewModelTest<HomeFragmentAction, HomeFragmentViewState, HomeFragmentSideEffect, HomeFragmentViewModel>() {

    @MockK
    private lateinit var abtest: Abtest

    @MockK
    private lateinit var petRepository: PetRepository

    @MockK
    private lateinit var resourcesProvider: ResourcesProvider

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setupViewModel() {
        MockKAnnotations.init(this)
        viewModel = HomeFragmentViewModel(
            abtest,
            petRepository,
            resourcesProvider,
            coroutinesTestRule.testDispatcherProvider
        )
    }

    @Test
    fun `Given a success getPets call and abtest showLastPet set to true, when view dispatches OnCreate action to viewModel, then assert view state`() {
        assertViewState(
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
        assertViewState(
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
        assertViewState(
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
        every { resourcesProvider.getString(R.string.pet_counter, "1") } returns "pet counter: 1"
        every { resourcesProvider.getString(R.string.last_pet_created, "pet") } returns "Last pet created: pet"
        every { resourcesProvider.getString(R.string.pet_counter_error) } returns "We could not retrieve any pet from our database"
    }

    private fun mockAbtest(value: Boolean) {
        every { abtest.showLastPet() } returns value
    }

    private suspend fun mockSuccessfulCallOfGetPets() {
        coEvery { petRepository.getPets() } returns flowOf(Result.success(listOf(Cat(name = "pet", description = "", url = ""))))
    }

    private suspend fun mockFailureCallOfGetPets() {
        coEvery {  petRepository.getPets() } returns flowOf(Result.failure(Exception()))
    }

}