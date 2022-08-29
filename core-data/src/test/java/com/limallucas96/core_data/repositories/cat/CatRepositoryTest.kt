package com.limallucas96.core_data.repositories.cat

import com.limallucas96.core_network.datasources.CatDataSource
import com.limallucas96.data_model.payloads.CatPayload
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CatRepositoryTest  {

    private lateinit var catRepository: CatRepository

    @Mock
    private lateinit var mockCatDataSource: CatDataSource

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        catRepository = CatRepositoryImp(testDispatcher, mockCatDataSource)
    }

    @Test
    fun `given an empty list of cats, when repository calls getCats, then assert that the list is empty`() =
        runBlockingTest {

            //given
            whenever(mockCatDataSource.getCats()).then { listOf<CatPayload>() }

            //when
            val cats = catRepository.getCats()

            //then
            assert(cats.isSuccess && cats.getOrNull()?.isEmpty() == true)
        }

    @Test
    fun `given a list of cats, when repository calls getCats, then assert that the list is not empty`() =
        runBlockingTest {

            //given
            whenever(mockCatDataSource.getCats()).then { listOf(CatPayload()) }

            //when
            val cats = catRepository.getCats()

            //then
            assert(cats.isSuccess && cats.getOrNull()?.isNotEmpty() == true)
        }

    @Test
    fun `given a list of cats with gif images, when repository calls getCats, then assert that the list is not empty and has no gif images`() =
        runBlockingTest {

            //mock
            val mock = (1..10).map { item -> CatPayload(url = if (item < 5) ".gif" else "") }

            //given
            whenever(mockCatDataSource.getCats()).then { mock }

            //when
            val cats = catRepository.getCats()

            //then
            assert(cats.isSuccess && cats.getOrNull()?.none { it.url == ".gif" } == true)
            assert(cats.isSuccess && cats.getOrNull()?.any { it.url == "" } == true)
        }

    @Test
    fun `given a fail api call, when repository calls getCats, then assert failure`() =
        runBlockingTest {

            //given
            whenever(mockCatDataSource.getCats()).then { Throwable() }

            //when
            val cats = catRepository.getCats()

            //then
            assert(cats.isFailure)
        }

}