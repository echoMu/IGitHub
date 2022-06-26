package com.echomu.github.data.remote

/**
 * Created by echoMu.
 */
class ResponseThrowable(
        var errorCode: Int,
        var errorMessage: String,
        throwable: Throwable
) : Exception(throwable)