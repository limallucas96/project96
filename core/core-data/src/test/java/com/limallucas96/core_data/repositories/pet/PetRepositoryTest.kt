package com.limallucas96.core_data.repositories.pet

import com.limallucas96.core_data.dispatchers.CoroutineTestRule
import com.limallucas96.core_database.datasource.PetDataSource
import com.limallucas96.core_database.entities.PetEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PetRepositoryTest {

    private lateinit var petRepository: PetRepository

    @MockK
    private lateinit var petDataSource: PetDataSource

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setup() {
        petRepository = PetRepositoryImp(coroutinesTestRule.testDispatcherProvider, petDataSource)
    }

    @Test
    fun `Given a success call in data source, when get pets is called from repository, then assert the call is successfully`() {
        runTest {
            launch(coroutinesTestRule.testDispatcherProvider.default) {

                //given
                coEvery { petDataSource.getPets() } returns flowOf(listOf())

                //when
                val pets = petRepository.getPets().first()

                //asserts
                assert(pets.isSuccess)
                assert(pets.getOrNull()?.isEmpty() == true)
            }
        }
    }

    @Test
    fun `Given a failure call in data source, when get pets is called from repository, then assert the call is failure`() {
        runTest {
            launch(coroutinesTestRule.testDispatcherProvider.default) {
                //given
                var isException = false
                coEvery { petDataSource.getPets() } throws Exception()

                //when
                try {
                    petRepository.getPets().first()
                } catch (ex: Exception) {
                    isException = true
                }

                //assert
                assert(isException)
            }
        }
    }

    // TODO check how to test insertPet unit test

}