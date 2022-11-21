package com.application.rssreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.rssreader.domain.usecase.GetRSSFeedsUseCase

class RSSFeedsViewModelFactory(
    private val getRSSFeedsUseCase: GetRSSFeedsUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RSSFeedsViewModel(getRSSFeedsUseCase) as T
    }
}









