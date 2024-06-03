package com.tomdroid.interview.brightwheelinterview

import ItemEntity
import Owner
import SearchRepositoriesResponseEntity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tomdroid.interview.brightwheel.common_data.remote.RepoService
import com.tomdroid.interview.brightwheel.common_data.remote.SearchService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainActivityVMTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repoService: RepoService = mockk()
    private val searchService: SearchService = mockk()

    private lateinit var viewModel: MainActivityVM

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainActivityVM(repoService, searchService)
    }

    @Test
    fun `fetchTopRepositoriesToDisplay updates viewStateFlow with fetched data`() = runBlockingTest {
        // Arrange
        val mockResponse = SearchRepositoriesResponseEntity(
            totalCount = 2,
            items = listOf(
                ItemEntity(
                    fullName = "Repo1/Repo1",
                    name = "Repo1",
                    owner = Owner(
                        login = "Owner1",
                        id = 123
                    )
                ),
                ItemEntity(
                    fullName = "Repo2/Repo2",
                    name = "Repo2",
                    owner = Owner(
                        login = "Owner2",
                        id = 456
                    )
                )
            )
        )
        coEvery { searchService.searchRepositories(any()) } returns mockResponse

        // Act
        viewModel.fetchTopRepositoriesToDisplay()
        val viewState = viewModel.viewStateFlow().first()

        // Assert
        assertEquals(false, viewState.loading)
        assertEquals(2, viewState.content.size)
        assertEquals("Repo1", viewState.content[0].repoName)
        assertEquals("Owner1", viewState.content[0].repoOwner)
        assertEquals("Repo2", viewState.content[1].repoName)
        assertEquals("Owner2", viewState.content[1].repoOwner)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
