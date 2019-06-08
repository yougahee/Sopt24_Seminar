package com.example.sopt24_seminar.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.example.sopt24_seminar.Adapter.ProductMainPagerAdapter
import com.example.sopt24_seminar.Adapter.SliderMainPagerAdapter
import com.example.sopt24_seminar.R
import com.example.sopt24_seminar.DB.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureMainTab()
        configureTitleBar()

        img_toolbar_main_action.setOnClickListener {
            if (SharedPreferenceController.getUserToken(this).isEmpty()) {
                startActivity<LoginActivity>()
            } else {
                SharedPreferenceController.clearUserToken(this)
                configureTitleBar()
            }
        }
    }

    //activity가 화면 최상단에 띄워질때마다 호출
    override fun onResume() {
        super.onResume()
        configureTitleBar()
    }

    private fun configureTitleBar() {
        if (SharedPreferenceController.getUserToken(this).isEmpty()) {
            img_toolbar_main_action.isSelected = false
        } else {
            img_toolbar_main_action.isSelected = true
        }
    }

    private fun configureMainTab() {
        vp_product_main_act.adapter = ProductMainPagerAdapter(supportFragmentManager, 3)
        vp_product_main_act.offscreenPageLimit = 2
        tl_category_main_act.setupWithViewPager(vp_product_main_act)

        val navCategoryMainLayout: View =
            (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.navigation_category_main, null, false)

        tl_category_main_act.getTabAt(0)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_all) as RelativeLayout
        tl_category_main_act.getTabAt(1)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_new) as RelativeLayout
        tl_category_main_act.getTabAt(2)!!.customView =
            navCategoryMainLayout.findViewById(R.id.rl_nav_category_main_end) as RelativeLayout

        vp_main_slider.adapter = SliderMainPagerAdapter(supportFragmentManager, 3)
        vp_main_slider.offscreenPageLimit = 2
        tl_main_indicator.setupWithViewPager(vp_main_slider)
    }
}
