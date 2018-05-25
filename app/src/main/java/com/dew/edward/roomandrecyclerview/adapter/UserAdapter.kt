package com.dew.edward.roomandrecyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dew.edward.roomandrecyclerview.R
import com.dew.edward.roomandrecyclerview.model.User
import kotlinx.android.synthetic.main.user_cell.view.*

/*
 * Created by Edward on 5/25/2018.
 */

class UserAdapter(val context: Context, val users: ArrayList<User>, val itemClick: (User) -> Unit): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_cell, parent, false)
        
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = users.count()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName
        holder.email.text = user.email
        holder.setOnItemClickListener(user)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val firstName = itemView.textCellFirstName
        val lastName = itemView.textCellLastName
        val email = itemView.textCellEmail
        fun setOnItemClickListener(user: User){
            itemView.setOnClickListener{itemClick(user)}
        }
    }
}