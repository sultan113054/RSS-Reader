package com.application.rssreader.presentation.state

import com.application.rssreader.core.exception.Failure

sealed class ViewState<T> {
    class Loading<T>(
        val data: T? = null,
    ) : ViewState<T>()

    class Success<T>(
        val data: T?,
        val message: String?,
    ) : ViewState<T>()

    class Error<T>(
        val statusCode: Int,
        val reason: Failure,
        val errorMessage: String?,
    ) : ViewState<T>()
}