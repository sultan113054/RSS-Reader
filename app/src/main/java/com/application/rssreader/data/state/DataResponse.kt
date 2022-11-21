package com.application.rssreader.data.state

import com.application.rssreader.core.exception.Failure
import com.application.rssreader.data.util.INTERNAL_ERROR

sealed class DataResponse<T>

data class DataSuccessResponse<T>(
    val data: T? = null,
    val message: String? = null,
) : DataResponse<T>()

data class DataErrorResponse<T>(
    val statusCode: Int = INTERNAL_ERROR,
    val reason: Failure = Failure.NetworkConnection,
    val errorMessage: String? = null,
) : DataResponse<T>()