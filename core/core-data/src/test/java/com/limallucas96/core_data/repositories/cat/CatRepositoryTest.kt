package com.limallucas96.core_data.repositories.cat

import com.limallucas96.core_data.dispatchers.CoroutineTestRule
import com.limallucas96.core_network.datasources.CatDataSource
import com.limallucas96.data_model.payloads.CatPayload
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CatRepositoryTest {

    private lateinit var catRepository: CatRepository

    @MockK
    private lateinit var mockCatDataSource: CatDataSource

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @Before
    fun setup() {
        catRepository = CatRepositoryImp(coroutinesTestRule.testDispatcherProvider, mockCatDataSource)
    }

    @Test
    fun `given an empty list of cats, when repository calls getCats, then assert that the list is empty`() =
        runTest {
            launch(coroutinesTestRule.testDispatcherProvider.default) {

                //given
                coEvery { mockCatDataSource.getCats() } returns listOf()

                //when
                val cats = catRepository.getCats()

                //then
                assert(cats.isSuccess && cats.getOrNull()?.isEmpty() == true)
            }
        }

    @Test
    fun `given a list of cats, when repository calls getCats, then assert that the list is not empty`() =
        runTest {
            launch(coroutinesTestRule.testDispatcherProvider.default) {

                //given
                coEvery { mockCatDataSource.getCats() } returns listOf(CatPayload())

                //when
                val cats = catRepository.getCats()

                //then
                assert(cats.isSuccess && cats.getOrNull()?.isNotEmpty() == true)
            }
        }

    @Test
    fun `given a list of cats with gif images, when repository calls getCats, then assert that the list is not empty and has no gif images`() =
        runTest {
            launch(coroutinesTestRule.testDispatcherProvider.default) {

                //mock
                val mock = (1..10).map { item -> CatPayload(url = if (item < 5) ".gif" else "") }

                //given
                coEvery { mockCatDataSource.getCats() } returns mock

                //when
                val cats = catRepository.getCats()

                //then
                assert(cats.isSuccess && cats.getOrNull()?.none { it.url == ".gif" } == true)
                assert(cats.isSuccess && cats.getOrNull()?.any { it.url == "" } == true)
            }
        }

    @Test
    fun `given a fail api call, when repository calls getCats, then assert failure`() =
        runTest {
            launch(coroutinesTestRule.testDispatcherProvider.default) {

                //given
                coEvery {  mockCatDataSource.getCats() } throws Throwable()

                //when
                val cats = catRepository.getCats()

                //then
                assert(cats.isFailure)
            }
        }

}