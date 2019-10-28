package com.assesment.discovery.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assesment.discovery.R
import com.assesment.discovery.model.data.UserPost

class TitleListAdapter(private val userPosts: List<UserPost>) : RecyclerView.Adapter<TitleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_title_item, parent, false)
        return TitleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userPosts.size
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        val userPost = userPosts[position]
        holder.bind(userPost)
    }
}