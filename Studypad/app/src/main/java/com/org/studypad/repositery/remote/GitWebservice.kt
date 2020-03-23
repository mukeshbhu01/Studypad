package com.org.studypad.repositery.remote

import com.org.studypad.repositery.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for calling remote web service with help to retrofit
 */
interface GitWebservice {
    /**
     * GET request path from url https://api.github.com/search/users?q=alphabetagama
     */
    @GET("/search/users")
    fun getGitServiceData(@Query("q") q : String) : Call<User>
}