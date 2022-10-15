package com.rehan.roomdatabase.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rehan.roomdatabase.R
import com.rehan.roomdatabase.models.User

class UserAdapter(private val context: Context, private var userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = userList[position]
        holder.name.text = user.name
        holder.age.text = user.age.toString()
    }

    override fun getItemCount(): Int = userList.size

    // First we will get data from database and then we will set here
    fun setData(userList: ArrayList<User>){
        this.userList = userList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val age: TextView = itemView.findViewById(R.id.tvAge)
    }
}