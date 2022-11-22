package com.application.rssreader.domain.usecase

import com.application.rssreader.DataState
import com.application.rssreader.core.exception.Failure
import com.application.rssreader.data.Fakes
import com.application.rssreader.data.model.RSSFeedModel
import com.application.rssreader.data.state.DataErrorResponse
import com.application.rssreader.data.state.DataSuccessResponse
import com.application.rssreader.domain.repository.RSSFeedsRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRSSFeedsUseCaseTest {
    private lateinit var testSubject: GetRSSFeedsUseCase

    @Mock
    private lateinit var mockRssFeedsRepository: RSSFeedsRepository

    @Before
    fun setup() {
        testSubject = GetRSSFeedsUseCase(mockRssFeedsRepository)
    }

    @Test
    fun getRSSFeeds_Success() {
        runBlocking {
            Mockito.`when`(mockRssFeedsRepository.getRSSFeedsList())
                .thenReturn(
                    flow {
                        emit(DataState.Success(DataSuccessResponse(Fakes.getRSSFeeds())))
                    })

            val resource = testSubject.getRSSFeedsList()
            resource.collect {
                when (it) {
                    is DataState.Success -> handleSuccess(it.dataResponse)
                    is DataState.Error -> handleFailure(it.dataResponse)
                    else -> {
                    }
                }
            }
        }
    }

    @Test
    fun getRSSFeeds_Failure() {
        runBlocking {

            Mockito.`when`(mockRssFeedsRepository.getRSSFeedsList())
                .thenReturn(
                    flow {
                        emit(DataState.Error(DataErrorResponse(reason = Failure.ServerError)))
                    })

            val resource = testSubject.getRSSFeedsList()

            resource.collect {
                when (it) {
                    is DataState.Success -> handleSuccess(it.dataResponse)
                    is DataState.Error -> handleFailure(it.dataResponse)
                    else -> {
                    }
                }
            }
        }
    }

    private fun handleSuccess(dataSuccessResponse: DataSuccessResponse<List<RSSFeedModel>>) {
        Truth.assertThat(dataSuccessResponse.data?.get(0)).isEqualTo(Fakes.getRSSFeeds()[0])
    }

    private fun handleFailure(dataErrorResponse: DataErrorResponse<List<RSSFeedModel>>) {
        Truth.assertThat(dataErrorResponse.reason).isEqualTo(Failure.ServerError)

    }
}