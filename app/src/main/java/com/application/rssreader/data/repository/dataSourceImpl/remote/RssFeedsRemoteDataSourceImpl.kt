package com.application.rssreader.data.repository.dataSourceImpl.remote

import com.application.rssreader.DataState
import com.application.rssreader.core.exception.Failure
import com.application.rssreader.data.repository.dataSource.remote.RssFeedsRemoteDataSource
import com.application.rssreader.data.state.DataErrorResponse
import com.application.rssreader.data.state.DataSuccessResponse
import com.prof.rssparser.Channel
import com.prof.rssparser.Parser


class RssFeedsRemoteDataSourceImpl(private val parser: Parser, private val url: String) :
    RssFeedsRemoteDataSource {
    override suspend fun getRssFeeds(): DataState<Channel> {
        return try {
            var channel=parser.getChannel(url)
            DataState.Success(DataSuccessResponse(channel))
        } catch (e: Exception) {
            DataState.Error(DataErrorResponse(errorMessage = e.message,
                reason = Failure.ServerError))
        }
    }

}