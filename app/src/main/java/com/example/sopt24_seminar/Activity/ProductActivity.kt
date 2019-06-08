package com.example.sopt24_seminar.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.sopt24_seminar.Adapter.ProductWebtoonListRecyclerViewAdapter
import com.example.sopt24_seminar.Data.WebtoonListData
import com.example.sopt24_seminar.R
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.toolbar_product.*

class ProductActivity : AppCompatActivity() {

    lateinit var productWebtoonListRecyclerViewAdapter: ProductWebtoonListRecyclerViewAdapter
    var product_id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        ConfigureTitleBar()
        ConfigureRecyclerView()

    }

    private fun ConfigureTitleBar() {
        var title = intent.getStringExtra("title")
        tv_toolbar_product_title.text = title
        product_id = intent.getIntExtra("idx",0)

        btn_toolbar_product_like.setOnClickListener {
            btn_toolbar_product_like.isSelected = !btn_toolbar_product_like.isSelected
        }
        btn_toolbar_product_back.setOnClickListener {
            finish()
        }
    }

    private fun ConfigureRecyclerView() {

        var dataList : ArrayList<WebtoonListData> = ArrayList()
        dataList.add(WebtoonListData(product_id, 0, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
            "1화. 문어지지말자!", 1, "2019-04-01"))
        dataList.add(WebtoonListData(product_id, 1, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
            "2화. 문어지지말자 우리!", 10, "2019-04-02"))
        dataList.add(WebtoonListData(product_id, 2, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
            "3화. 타코야끼를 먹다.", 17, "2019-04-03"))
        dataList.add(WebtoonListData(product_id, 3, "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
            "4화. 문어숙회를 먹다.", 14, "2019-04-04"))

        productWebtoonListRecyclerViewAdapter = ProductWebtoonListRecyclerViewAdapter(this, dataList)
        rv_webtoonlist_product_overview_list!!.adapter = productWebtoonListRecyclerViewAdapter
        rv_webtoonlist_product_overview_list!!.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }

}
