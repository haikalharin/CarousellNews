package com.haikal.carousellTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haikal.carousellTest.presentation.news.NewsViewModel
import com.paem.core.data.NewsRepository
import com.paem.core.data.remote.NetworkState
import com.paem.core.data.remote.ProcessState
import com.paem.core.data.remote.RequestState
import com.paem.core.data.remote.model.NewsResponse
import com.paem.core.entities.News
import com.paem.core.utils.toNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var newsRepository: NewsRepository

    private lateinit var viewModel: NewsViewModel
    val dispatcher = TestCoroutineDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = NewsViewModel(newsRepository)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getNews should return success with data`() = runTest {
        // Arrange
        val newsList = listOf(
            News(
                "Title1",
                1532939458,
                4,
                "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-hero-image_10june.png",
                "123",
                "Southeast Asia-based mobile listings startup Carousell raises $85M"
            ),
            News(
                "Title2",
                1532939458,
                4,
                "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-hero-image_10june.png",
                "123",
                "Southeast Asia-based mobile listings startup Carousell raises $85M"
            )
        )
        val newsResponse: List<NewsResponse> = newsList.map { it.toNewsResponse() }
        val successState = ProcessState.Success(newsResponse)
        `when`(newsRepository.getNews()).thenReturn(successState)

        // Act
        var result: RequestState<List<News>>? = null
        viewModel.getNews {
            result = it
        }

        // Assert
        assertTrue(result is RequestState.Success)
        assertEquals(newsList.size, (result as RequestState.Success).result.size)
        verify(newsRepository).getNews()
    }

    @Test
    fun `getNews should return success with empty list`() = runTest {
        // Arrange
        val successState = ProcessState.Success(emptyList<NewsResponse>())
        `when`(newsRepository.getNews()).thenReturn(successState)

        // Act
        var result: RequestState<List<News>>? = null
        viewModel.getNews {

            result = it
        }

        // Assert
        assertTrue(result is RequestState.Success)
        assertTrue((result as RequestState.Success).result.isEmpty())
        verify(newsRepository).getNews()
    }

    @Test
    fun `getNews should return failed state`() = runTest {
        // Arrange
        val errorMessage = NetworkState.Failed.ByErrorMessage("Error occurred")
        val failedState = ProcessState.Failed(NetworkState.Failed.ByErrorMessage("Error occurred"))
        `when`(newsRepository.getNews()).thenReturn(failedState)

        // Act
        var result: RequestState<List<News>>? = null
        viewModel.getNews {
            result = it
        }

        // Assert
        assertTrue(result is RequestState.Failed)
        assertEquals(errorMessage, (result as RequestState.Failed).error)
        verify(newsRepository).getNews()
    }
}
