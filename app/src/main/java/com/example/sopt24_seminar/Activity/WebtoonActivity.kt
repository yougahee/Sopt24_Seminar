package com.example.sopt24_seminar.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt24_seminar.R
import kotlinx.android.synthetic.main.toolbar_product.*
import kotlinx.android.synthetic.main.toolbar_webtoon.*
import org.jetbrains.anko.startActivity

class WebtoonActivity : AppCompatActivity() {

    var product_id = -1
    var episode_id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webtoon)

        ConfigureTitleBar()
    }

    private fun ConfigureTitleBar(){
        var title = intent.getStringExtra("title")
        tv_toolbar_webtoon_title.text = title

        product_id = intent.getIntExtra("idx",-1)
        episode_id = intent.getIntExtra("episode_id",-1)
        if(product_id == -1 || episode_id == -1) finish()

        btn_toolbar_webtoon_like.setOnClickListener {
            startActivity<CommentActivity>()
        }

        btn_toolbar_webtoon_back.setOnClickListener {
            finish()
        }

    }
}
