package com.example.sopt24_seminar.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt24_seminar.Adapter.ProductOverviewRecyclerViewAdapter
import com.example.sopt24_seminar.Data.ProductOverviewData
import com.example.sopt24_seminar.Network.ApplicationController
import com.example.sopt24_seminar.Network.Get.GetMainProductListResponse
import com.example.sopt24_seminar.Network.NetworkService

import com.example.sopt24_seminar.R
import kotlinx.android.synthetic.main.fragment_all_product_main.*
import kotlinx.android.synthetic.main.fragment_end_product_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EndProductMainFragment : Fragment() {

    lateinit var productOverviewRecyclerViewAdapter: ProductOverviewRecyclerViewAdapter

    val networkService : NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_product_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var dataList : ArrayList<ProductOverviewData> = ArrayList()
     /*   dataList.add(
            ProductOverviewData(
                "http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",0,"완결작품1", 120, "완결작가A"
            )
        )
        dataList.add(ProductOverviewData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
            1,"완결작품2", 130, "완결작가A"))
        dataList.add(ProductOverviewData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
            2,"완결작품3", 10, "완결작가A"))
        dataList.add(ProductOverviewData("http://sopt.org/wp/wp-content/uploads/2014/01/24_SOPT-LOGO_COLOR-1.png",
            3,"완결작품4", 180, "완결작가A"))*/


        productOverviewRecyclerViewAdapter = ProductOverviewRecyclerViewAdapter(context!!, dataList)
        rv_product_overview_end.adapter =productOverviewRecyclerViewAdapter
        rv_product_overview_end.layoutManager = GridLayoutManager(context!!, 3)

        getMainProductListResponse()
    }

    private fun getMainProductListResponse() {
        val getMainProductListResponse = networkService.getMainProductListResponse(
            "application/json", 3 )
        getMainProductListResponse.enqueue(object : Callback<GetMainProductListResponse> {
            override fun onFailure(call: Call<GetMainProductListResponse>, t: Throwable) {
                Log.e("AllMainPro List failed", t.toString())
            }

            override fun onResponse(
                call: Call<GetMainProductListResponse>,
                response: Response<GetMainProductListResponse>
            ) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 201) {
                        val tmp : ArrayList<ProductOverviewData> = response.body()!!.data!!
                        productOverviewRecyclerViewAdapter.dataList = tmp
                        productOverviewRecyclerViewAdapter.notifyDataSetChanged()
                    }

                }

            }

        })
    }


}
