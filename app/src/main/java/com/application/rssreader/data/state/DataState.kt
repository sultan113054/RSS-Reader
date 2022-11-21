package com.application.rssreader

import com.application.rssreader.data.state.DataErrorResponse
import com.application.rssreader.data.state.DataSuccessResponse

sealed class DataState<T> {
    class Success<T>(val dataResponse : DataSuccessResponse<T>) : DataState<T>()
    class Error<T>(val dataResponse : DataErrorResponse<T>) : DataState<T>()

}