package com.org.studypad.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.org.studypad.Utility
import com.org.studypad.repositery.local.UserLocalRepository
import com.org.studypad.repositery.model.User
import com.org.studypad.repositery.model.UserInfo
import com.org.studypad.repositery.remote.UserRemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * View model for hold data to support UserHomeActivity
 */
class UserViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var userdata: MutableLiveData<User>? = MutableLiveData()
    private var userRemoteRepository: UserRemoteRepository? = null
    private var userLocalRepository: UserLocalRepository? = null

    fun loadUserData(context: Context, query: String) {
        if (userRemoteRepository == null) {
            userRemoteRepository = UserRemoteRepository()
            Log.d(UserViewModel::class.java.name, "LoadUserData : $query")
            getAllUserDataFromLocal(context, query)
        }
    }

    private fun getAllUserDataFromLocal(context: Context, query: String) {
        Log.d(UserViewModel::class.java.name, "getAllUserDataFromLocal")
        if (userLocalRepository == null) {
            userLocalRepository = UserLocalRepository(context)
        }
        var userInfoList : List<UserInfo>?

    /*    val liveData: MutableLiveData<User> = MutableLiveData()*/

        launch {
            userInfoList = userLocalRepository?.getAllUserInfoLocal()
            if(userInfoList?.isNotEmpty()!!) {
                val user = User(0, false, userInfoList)
                userdata?.value = user
            }else{
                if(Utility().isNetworkAvailable(context))
                userdata?.let { userRemoteRepository?.loadUserData(query, userLocalRepository, it) }
                else{
                    Utility().showAlertDialog(context)
                }
            }
        }
    }
}