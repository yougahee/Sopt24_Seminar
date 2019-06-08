package com.example.sopt24_seminar.Adapter

import android.content.Context
import android.graphics.Rect
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sopt24_seminar.Data.CommentData
import com.example.sopt24_seminar.R

class CommentRecyclerViewAdapter(val ctx : Context , val dataList : ArrayList<CommentData>) :  RecyclerView.Adapter<CommentRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_comment, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].img_url)
            .into(holder.img_url)
        holder.author.text = dataList[position].author
        holder.date.text = dataList[position].date
        holder.description.text = dataList[position].comment

    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var img_url = itemView!!.findViewById(R.id.img_rv_comment_overview) as ImageView
        var author = itemView!!.findViewById(R.id.tv_rv_item_comment_author) as TextView
        var date = itemView!!.findViewById(R.id.tv_rv_item_comment_date) as TextView
        var description = itemView!!.findViewById(R.id.tv_rv_item_comment_description) as TextView
    }
}