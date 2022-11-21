package com.sevenpeakssoftware.sultan.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.rssreader.data.model.RSSFeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RSSFeedsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articles: List<RSSFeedEntity>)

    @Query("SELECT * FROM rss_feed")
    fun getRSSFeeds(): Flow<List<RSSFeedEntity>>

    @Query("DELETE  FROM rss_feed")
    suspend fun deleteRSSFeeds()
}