package com.application.rssreader.presentation.util

import com.application.rssreader.core.exception.Failure

class RSSFeedsFailure {
    class NoDataAvailable : Failure.FeatureFailure()
}
