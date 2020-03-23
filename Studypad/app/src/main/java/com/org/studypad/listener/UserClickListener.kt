package com.org.studypad.listener

import com.org.studypad.repositery.model.UserInfo

/**
 * Listener for user item select
 */
interface UserClickListener {
    fun onSelectUser(user : UserInfo)
}