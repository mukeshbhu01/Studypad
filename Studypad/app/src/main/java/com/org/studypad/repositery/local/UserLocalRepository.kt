package com.org.studypad.repositery.local

import android.content.Context
import android.util.Log
import com.org.studypad.localdb.UserDAO
import com.org.studypad.localdb.UserRoomDatabase
import com.org.studypad.localdb.entity.UserLocalInfo
import com.org.studypad.repositery.model.UserInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Repository for all local data base related operation
 */
class UserLocalRepository(context: Context) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var userDao: UserDAO = UserRoomDatabase.getDatabase(context).userDAO()

    /**
     * Methods work for prepared table format (Entity) data from model data
     * to persist into db.
     *
     */
    private fun prepareDataInTableModel(usersInfo: List<UserInfo>): ArrayList<UserLocalInfo> {
        val users = arrayListOf<UserLocalInfo>()
        if (usersInfo.isNotEmpty()) {
            for (info in usersInfo) {
                val user = UserLocalInfo()

                user.login = info.login
                user.id = info.id
                user.avatarUrl = info.avatarUrl
                user.events_url = info.events_url
                user.followersUrl = info.followersUrl
                user.followingUrl = info.followingUrl
                user.gistsUrl = info.gistsUrl
                user.gravatarId = info.gravatarId
                user.htmlUrl = info.htmlUrl
                user.nodeId = info.nodeId
                user.organizationsUrl = info.organizationsUrl
                user.received_events_url = info.received_events_url
                user.repos_url = info.repos_url
                user.score = info.score
                user.siteAdmin = info.siteAdmin
                user.starredUrl = info.starredUrl
                user.subscriptionsUrl = info.subscriptionsUrl
                user.type = info.type
                user.url = info.url

                users.add(user)
            }
        }
        return users
    }

    /**
     * Get all user information from room db with dao.getAll() : ArrayList<UserInfo>
     */
    suspend fun getAllUserInfoLocal(): ArrayList<UserInfo>? {
        val userLocalInfoDbModelList: ArrayList<UserLocalInfo> = userDao.getAllUsers() as ArrayList<UserLocalInfo>
        return loadFromLocalDB(userLocalInfoDbModelList)
    }

    /**
     * Save user data into db
     */
     fun saveUserInfo(usersInfoList: List<UserInfo>) {
         Log.d(UserLocalRepository::class.java.name, "saveUserInfo : $usersInfoList")
        val userLocalInfo: ArrayList<UserLocalInfo>? = prepareDataInTableModel(usersInfoList)
            launch {
                userDao.addUser(userLocalInfo)
            }
    }

    /**
     * Prepare model data from local db model data
     */
    private fun loadFromLocalDB(userLocalInfo: ArrayList<UserLocalInfo>): ArrayList<UserInfo> {
        val userInfoList = arrayListOf<UserInfo>()
        if (userLocalInfo.isNotEmpty()) {
            for (user in userLocalInfo) {
                val info = UserInfo()

                info.login = user.login
                info.id = user.id
                info.avatarUrl = user.avatarUrl
                info.events_url = user.events_url
                info.followersUrl = user.followersUrl
                info.followingUrl = user.followingUrl
                info.gistsUrl = user.gistsUrl
                info.gravatarId = user.gravatarId
                info.htmlUrl = user.htmlUrl
                info.nodeId = user.nodeId
                info.organizationsUrl = user.organizationsUrl
                info.received_events_url = user.received_events_url
                info.repos_url = user.repos_url
                info.score = user.score
                info.siteAdmin = user.siteAdmin
                info.starredUrl = user.starredUrl
                info.subscriptionsUrl = user.subscriptionsUrl
                info.type = user.type
                info.url = user.url

                userInfoList.add(info)
            }
        }
        return userInfoList
    }
}