package com.echomu.github.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by echoMu.
 */
data class ListData<T>(
        @SerializedName("total_count") val totalCount: Int? = null,
        @SerializedName("incomplete_results") val incompleteResults: Boolean? = null,
        var items: List<T>? = null
)