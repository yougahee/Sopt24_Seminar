package com.example.sopt24_seminar.Adapter

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.sopt24_seminar.Fragment.SliderMainFragment

class SliderMainPagerAdapter(fm: FragmentManager?, val num_fragment : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {

        var fragment : SliderMainFragment = SliderMainFragment()
        var bundle : Bundle = Bundle(1)

        when(p0) {
            0->bundle.putString("background_url", "https://img.huffingtonpost.com/asset/5c21caa51d0000250231b2a6.jpeg?ops=scalefit_630_noupscale")
            1->bundle.putString("background_url", "http://ninedog.cafe24.com//web/img/dogunderstand/fr1.jpg")
            2->bundle.putString("background_url", "http://www.seoulmilkblog.co.kr/wp/wp-content/uploads/2017/01/112.jpg")

        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return num_fragment
    }
}