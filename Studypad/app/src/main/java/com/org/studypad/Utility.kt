package com.org.studypad

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog

/**
 * Utility class
 */
class Utility {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

    fun showAlertDialog(context: Context) {
        val dialogBuilder = AlertDialog.Builder(context)
        // set message of alert dialog
        dialogBuilder.setMessage("Network Connection required")
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Close") { _, _ ->
                (context as Activity).finish()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Connection Alert!")
        // show alert dialog
        alert.show()
    }
}