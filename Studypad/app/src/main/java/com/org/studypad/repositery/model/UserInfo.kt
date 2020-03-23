package com.org.studypad.repositery.model

import com.google.gson.annotations.SerializedName

/**
 * User response item model mapping data class
 */
data class UserInfo(
    @SerializedName(value = "login")
    var login: String? = null,

    @SerializedName(value = "id")
    var id: Int = 0,

    @SerializedName(value = "node_id")
    var nodeId: String? = null,

    @SerializedName(value = "avatar_url")
    var avatarUrl: String? = null,

    @SerializedName(value = "gravatar_id")
    var gravatarId: String? = null,

    @SerializedName(value = "url")
    var url: String? = null,

    @SerializedName(value = "html_url")
    var htmlUrl: String? = null,

    @SerializedName(value = "followers_url")
    var followersUrl: String? = null,

    @SerializedName(value = "following_url")
    var followingUrl: String? = null,

    @SerializedName(value = "gists_url")
    var gistsUrl: String? = null,

    @SerializedName(value = "starred_url")
    var starredUrl: String? = null,

    @SerializedName(value = "subscriptions_url")
    var subscriptionsUrl: String? = null,

    @SerializedName(value = "organizations_url")
    var organizationsUrl: String? = null,

    @SerializedName(value = "repos_url")
    var repos_url: String? = null,

    @SerializedName(value = "events_url")
    var events_url: String? = null,

    @SerializedName(value = "received_events_url")
    var received_events_url: String? = null,

    @SerializedName(value = "type")
    var type: String? = null,

    @SerializedName(value = "site_admin")
    var siteAdmin: Boolean = false,

    @SerializedName(value = "score")
    var score: Int = 0
)
