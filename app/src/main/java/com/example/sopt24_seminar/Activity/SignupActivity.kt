package com.example.sopt24_seminar.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sopt24_seminar.Network.ApplicationController
import com.example.sopt24_seminar.Network.NetworkService
import com.example.sopt24_seminar.Network.Post.PostSignupResponse
import com.example.sopt24_seminar.R
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SignupActivity : AppCompatActivity() {

    val networkService : NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        btn_signupsubmit_signup_act.setOnClickListener {
            val signup_u_id = et_id_signup_act.text.toString()
            val signup_u_pw = et_pw_signup_act.text.toString()
            val signup_u_name = et_name_signup_act.text.toString()
            if(signup_u_id == "") et_id_signup_act.requestFocus()
            else if(signup_u_name == "") et_name_signup_act.requestFocus()
            else if(signup_u_pw == "") et_pw_signup_act.requestFocus()
            else postSignupResponse(signup_u_id, signup_u_pw, signup_u_name)
        }
    }

    fun postSignupResponse(u_id : String, u_pw : String, u_name : String)
    {
        var jsonObject = JSONObject()
        jsonObject.put("id", u_id)
        jsonObject.put("password", u_pw)
        jsonObject.put("name", u_name)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postSignupResponse : Call<PostSignupResponse> =
                networkService.postSignupResponse("application/json", gsonObject)
        postSignupResponse.enqueue(object : Callback<PostSignupResponse> {
            override fun onFailure(call: Call<PostSignupResponse>, t: Throwable) {
                Log.e("Login Failed", t.toString())
            }

            override fun onResponse(call: Call<PostSignupResponse>, response: Response<PostSignupResponse>) {
                if(response.isSuccessful){
                    toast(response.body()!!.message)
                    if(response.body()!!.status == 201){
                        val simpleDataFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val e_time = simpleDataFormat.format(Date())

                        val intent : Intent = Intent()
                        intent.putExtra("end_time", e_time)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
            }

        })

    }

}