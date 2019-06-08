package com.example.sopt24_seminar.Network.Post

data class PostLoginResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : String?
)