package com.application.rssreader.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "rss_feed"
)
data class RSSFeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String?,
    val description: String?,
    val thumbnail: String?,
    val link: String?,
)