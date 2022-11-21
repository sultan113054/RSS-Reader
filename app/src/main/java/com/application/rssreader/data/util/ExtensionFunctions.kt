package com.application.rssreader.data.util

import com.application.rssreader.data.model.RSSFeedEntity
import com.application.rssreader.data.model.RSSFeedModel
import com.prof.rssparser.Article
import com.application.rssreader.core.extension.RemoveHTMLTags

fun Article.asEntity() = RSSFeedEntity(0, title,
    description,
    image,
    link)

fun RSSFeedEntity.asModel() = RSSFeedModel(title,
    description?.RemoveHTMLTags(),
    thumbnail,
    link)