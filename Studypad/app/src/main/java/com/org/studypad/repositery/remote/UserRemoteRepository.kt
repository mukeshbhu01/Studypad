package com.org.studypad.repositery.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.org.studypad.UserHomeActivity
import com.org.studypad.repositery.local.UserLocalRepository
import com.org.studypad.repositery.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository for fetch data from remote server
 */
class UserRemoteRepository {
    private var gitService: GitWebservice? = null
    private val URL = "https://api.github.com"

    init {
        Log.d(UserRemoteRepository::class.java.name, "Remote service init...")
        val retrofit =
            Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        gitService = retrofit.create(GitWebservice::class.java)
    }


    /**
     * load data with retrofit service
     */
    fun loadUserData(
        query: String,
        userLocalRepository: UserLocalRepository?,liveData: MutableLiveData<User>) {
        Log.d(UserRemoteRepository::class.java.name, "loading start...")

        gitService?.getGitServiceData(query)?.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(UserRemoteRepository::class.java.name, "loading fail...")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d(UserRemoteRepository::class.java.name, "loading success from service...")
                liveData.value = response.body()
                liveData.value?.items?.let { userLocalRepository?.saveUserInfo(it) }
            }
        })
    }
}
