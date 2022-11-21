package com.application.rssreader.core.exception

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()
    object DBError : Failure()
    object Empty : Failure()
    object None:Failure()
    abstract class FeatureFailure : Failure()
}