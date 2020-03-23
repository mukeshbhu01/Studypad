package com.org.studypad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

/**
 * Show user info from respected URL
 */
class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        supportActionBar!!.title = "User Info"

        val link : String? = intent.getStringExtra("url")
        if (link != null) {
            initWebView(link)
        }else{
            Toast.makeText(this,"Url not found",Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Init web view and show
     */
    private fun initWebView(htmlLink : String){
        val webView = findViewById<WebView>(R.id.webView)
        Log.d(UserInfoActivity::class.java.name, "Html link : $htmlLink")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(htmlLink)
    }
}
