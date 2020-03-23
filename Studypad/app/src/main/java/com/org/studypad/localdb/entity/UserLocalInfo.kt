package com.org.studypad.localdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity model for room db
 */
@Entity(tableName = "user_info")
data class UserLocalInfo(
    @ColumnInfo(name = "login")
    var login: String? = "",

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "node_id")
    var nodeId: String? = "",

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = "",

    @ColumnInfo(name = "gravatar_id")
    var gravatarId: String? = "",

    @ColumnInfo(name = "url")
    var url: String? = "",

    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = "",

    @ColumnInfo(name = "followers_url")
    var followersUrl: String? = "",

    @ColumnInfo(name = "following_url")
    var followingUrl: String? = "",

    @ColumnInfo(name = "gists_url")
    var gistsUrl: String? = "",

    @ColumnInfo(name = "starred_url")
    var starredUrl: String? = "",

    @ColumnInfo(name = "subscriptions_url")
    var subscriptionsUrl: String? = "",

    @ColumnInfo(name = "organizations_url")
    var organizationsUrl: String? = "",

    @ColumnInfo(name = "repos_url")
    var repos_url: String? = "",

    @ColumnInfo(name = "events_url")
    var events_url: String? = "",

    @ColumnInfo(name = "received_events_url")
    var received_events_url: String? = "",

    @ColumnInfo(name = "type")
    var type: String? = "",

    @ColumnInfo(name = "site_admin")
    var siteAdmin: Boolean = false,

    @ColumnInfo(name = "score")
    var score: Int = -1
)
