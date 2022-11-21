package com.application.rssreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.rssreader.DataState
import com.application.rssreader.data.model.RSSFeedModel
import com.application.rssreader.data.state.DataErrorResponse
import com.application.rssreader.data.state.DataSuccessResponse
import com.application.rssreader.domain.usecase.GetRSSFeedsUseCase
import com.application.rssreader.presentation.state.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RSSFeedsViewModel(
    private val getRSSFeedsUseCase: GetRSSFeedsUseCase,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ViewState<List<RSSFeedModel>>>(ViewState.Loading())
    val uiState: StateFlow<ViewState<List<RSSFeedModel>>> = _uiState

    fun getRSSFeeds() {
        viewModelScope.launch(Dispatchers.IO) {
            getRSSFeedsUseCase.getRSSFeedsList().collect {
                when (it) {
                    is DataState.Success -> handleSuccess(it.dataResponse)
                    is DataState.Error -> handleFailure(it.dataResponse)
                    else -> _uiState.value = ViewState.Loading()
                }
            }
        }
    }


    private fun handleFailure(dataErrorResponse: DataErrorResponse<List<RSSFeedModel>>) {
        _uiState.value = ViewState.Error(statusCode = dataErrorResponse.statusCode,
            reason = dataErrorResponse.reason,
            errorMessage = dataErrorResponse.errorMessage)
    }

    private fun handleSuccess(dataSuccessResponse: DataSuccessResponse<List<RSSFeedModel>>) {
        _uiState.value =
            ViewState.Success(dataSuccessResponse.data, message = dataSuccessResponse.message)
    }
}














