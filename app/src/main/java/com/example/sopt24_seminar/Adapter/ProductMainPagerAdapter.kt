package com.example.sopt24_seminar.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.sopt24_seminar.Fragment.AllProductMainFragment
import com.example.sopt24_seminar.Fragment.EndProductMainFragment
import com.example.sopt24_seminar.Fragment.NewProductMainFragment

class ProductMainPagerAdapter(fm:FragmentManager, private  val num_fragment : Int) : FragmentStatePagerAdapter(fm) {

    companion object {
        private var allProductMainFragment : AllProductMainFragment? = null
        private var endProductMainPagerAdapter : EndProductMainFragment? = null
        private var newProductMainPagerAdapter : NewProductMainFragment? = null

        @Synchronized
        fun getAllProductMainFragment() : AllProductMainFragment {
            if(allProductMainFragment == null) allProductMainFragment = AllProductMainFragment()
            return allProductMainFragment!!

        }

        @Synchronized
        fun getEndProductMainFragment() : EndProductMainFragment {
            if(endProductMainPagerAdapter == null) endProductMainPagerAdapter = EndProductMainFragment()
            return endProductMainPagerAdapter!!

        }

        @Synchronized
        fun getNewProductMainFragment() : NewProductMainFragment {
            if(newProductMainPagerAdapter == null) newProductMainPagerAdapter = NewProductMainFragment()
            return newProductMainPagerAdapter!!

        }
    }
    override fun getItem(p0: Int): Fragment? {
        return when(p0){

            0 -> getAllProductMainFragment()
            1 -> getNewProductMainFragment()
            2 -> getEndProductMainFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return num_fragment
    }


}