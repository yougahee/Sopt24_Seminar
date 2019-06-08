package com.example.sopt24_seminar.Adapter

import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sopt24_seminar.Activity.WebtoonActivity
import com.example.sopt24_seminar.Data.WebtoonListData
import com.example.sopt24_seminar.R
import org.jetbrains.anko.startActivity

class ProductWebtoonListRecyclerViewAdapter(val ctx : Context, val dataList : ArrayList<WebtoonListData>) : RecyclerView.Adapter<ProductWebtoonListRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_webtoonlist_overview, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(ctx).load(dataList[position].img).into(holder.img_url)
        holder.title.text = dataList[position].title

        holder.description.text = "조회수 " + dataList[position].num_view + "만회     "
                dataList[position].description
        holder.container.setOnClickListener {
            ctx.startActivity<WebtoonActivity>(
                "title" to dataList[position].title,
                "idx" to dataList[position].product_id,
                "episode_id" to dataList[position].webtoon_id
            )
        }
    }


    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView!!.findViewById(R.id.ll_rv_item_webtoonlist_container) as LinearLayout
        var img_url = itemView!!.findViewById(R.id.img_rv_webtoonlist_overview) as ImageView
        var title = itemView!!.findViewById(R.id.tv_rv_item_webtoonlist_overview_title) as TextView
        var description = itemView!!.findViewById(R.id.tv_rv_item_webtoonlist_overview_description) as TextView
    }
}