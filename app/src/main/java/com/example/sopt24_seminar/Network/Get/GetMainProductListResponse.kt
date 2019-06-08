package com.example.sopt24_seminar.Network.Get

import com.example.sopt24_seminar.Data.ProductOverviewData

data class GetMainProductListResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : ArrayList<ProductOverviewData>?
)