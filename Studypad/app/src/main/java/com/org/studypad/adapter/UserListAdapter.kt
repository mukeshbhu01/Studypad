package com.org.studypad.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.org.studypad.R
import com.org.studypad.UserHomeActivity
import com.org.studypad.repositery.model.UserInfo
import kotlinx.android.synthetic.main.cell_list_item.view.*

/**
 * User home recycler adapter
 */
class UserListAdapter(private var userInfo: List<UserInfo>?, private val context : Context) :
    RecyclerView.Adapter<UserListAdapter.UserHolder>() {

    private lateinit var userSelection: UserHomeActivity.UserSelection

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.cell_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userInfo?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val data = userInfo?.get(position)
        holder.itemView.tvUserName.text = data?.login
        Glide.with(context).load(data?.avatarUrl).into(holder.itemView.ivUserAvtr)
        holder.itemView.setOnClickListener {
            if (data != null) {
                userSelection.onSelectUser(data)
            }
        }
    }

    /**
     * listener for item selection
     */
    fun addItemListener(userSelection: UserHomeActivity.UserSelection) {
        this.userSelection = userSelection
    }

    /**
     * recycler item notify data set change
     */
    fun notifyDataUpdate(userInfo: List<UserInfo>) {
        this.userInfo = userInfo
        notifyDataSetChanged()
    }

    /**
     * View holder class for recycler view item
     */
    class UserHolder(view: View) : RecyclerView.ViewHolder(view)

}