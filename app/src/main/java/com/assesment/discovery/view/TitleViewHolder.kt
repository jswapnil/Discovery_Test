package com.assesment.discovery.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.assesment.discovery.model.data.UserPost
import kotlinx.android.synthetic.main.adapter_title_item.view.*

class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(userPost: UserPost) {
        itemView.tv_title.text = userPost.title
        itemView.tv_body.text = userPost.body
    }


}