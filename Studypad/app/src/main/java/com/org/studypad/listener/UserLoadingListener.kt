package com.org.studypad.listener

/**
 * Listener for loading state monitor
 */
interface UserLoadingListener {
    fun onLoadingStart()
    fun onLoadingSuccess()
    fun onLoadFail()
}