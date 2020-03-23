package com.org.studypad

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.org.studypad.adapter.UserListAdapter
import com.org.studypad.listener.UserClickListener
import com.org.studypad.listener.UserLoadingListener
import com.org.studypad.repositery.model.User
import com.org.studypad.repositery.model.UserInfo
import com.org.studypad.viewmodel.UserViewModel


/**
 * UserHomeActivity, holds list of users
 */

class UserHomeActivity : AppCompatActivity() {

    private lateinit var model: UserViewModel
    private lateinit var adapter: UserListAdapter
    private lateinit var recyclerList: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    /**
     * Initialize home ui related views
     */
    private fun initView() {
        recyclerList = findViewById(R.id.itemListRecycler)
        progressBar = findViewById(R.id.progressBar)
        setupRecyclerView(emptyList())
        initModelData()
    }

    /**
     * Initialise data source for home view, created view model, and add
     * observer for view model change/update
     */
    private fun initModelData() {
        model = ViewModelProvider(this).get(UserViewModel::class.java)
        //call for load data from respective repo
        loadUserData()
        //observer
        model.userdata?.observe(this, Observer<User> { user ->
            progressBar.visibility = View.GONE
            Log.d(UserHomeActivity::class.java.name, "View Model Observe : $user")
            user.items?.let { notifyRecyclerDataChange(it) }
        })
    }

    private fun loadUserData() {
        progressBar.visibility = View.VISIBLE
        model.loadUserData(this, "alphabetagama")
    }

    /**
     * Setup recycler view
     */
    private fun setupRecyclerView(userInfo: List<UserInfo>) {
        recyclerList.layoutManager = LinearLayoutManager(this)
        adapter = UserListAdapter(userInfo, this)
        adapter.addItemListener(UserSelection())
        recyclerList.adapter = adapter
    }


    /**
     * Notify recycler adapter data update
     */
    private fun notifyRecyclerDataChange(userInfo: List<UserInfo>) {
        adapter.notifyDataUpdate(userInfo)
    }

    /**
     * User info selection listener
     * Launch target web view activity to show web page
     */
    inner class UserSelection : UserClickListener {
        override fun onSelectUser(user: UserInfo) {
            val intent = Intent(this@UserHomeActivity, UserInfoActivity::class.java)
            intent.putExtra("url", user.htmlUrl)
            this@UserHomeActivity.startActivity(intent)
        }
    }
}
