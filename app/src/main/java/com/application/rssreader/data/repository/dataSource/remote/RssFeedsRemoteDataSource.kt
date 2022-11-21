package com.application.rssreader.data.repository.dataSource.remote

import com.application.rssreader.DataState
import com.prof.rssparser.Channel


interface RssFeedsRemoteDataSource {
    suspend fun getRssFeeds(): DataState<Channel>
}