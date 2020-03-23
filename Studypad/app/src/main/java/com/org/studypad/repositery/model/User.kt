package com.org.studypad.repositery.model

import com.google.gson.annotations.SerializedName

/**
 * User mapped data class for response main container
 */
data class  User (
    @SerializedName(value = "total_count")
    val totalCount: Int = 0,

    @SerializedName(value = "incomplete_results")
    val incompleteResults: Boolean = false,

    var items: List<UserInfo>? = null
)
