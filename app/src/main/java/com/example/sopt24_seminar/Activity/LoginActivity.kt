package com.example.sopt24_seminar.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sopt24_seminar.R
import com.example.sopt24_seminar.DB.SharedPreferenceController
import com.example.sopt24_seminar.Network.ApplicationController
import com.example.sopt24_seminar.Network.NetworkService
import com.example.sopt24_seminar.Network.Post.PostLoginResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    val REQUEST_CODE_LOGIN_ACTIVITY = 1000

    val networkService : NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_id_login_act.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        et_pw_login_act.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.yellow_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        //로그인버튼을 누르면
        btn_loginsubmit_login_act.setOnClickListener {
            val login_u_id = et_id_login_act.text.toString()
            val login_u_pw = et_pw_login_act.text.toString()

            if(login_u_id == "") et_id_login_act.requestFocus()
            else if(login_u_pw == "") et_pw_login_act.requestFocus()
            else postLoginResponse(login_u_id, login_u_pw)
        }

        tv_signup_login_act.setOnClickListener{

            val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            //시간을 string형태로 가져오는 것
            val s_time = simpleDateFormat.format(Date())

            startActivityForResult<SignupActivity>(REQUEST_CODE_LOGIN_ACTIVITY, "start_time" to s_time)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_LOGIN_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                val e_time = data!!.getStringExtra("end_time")

                toast("End time : ${e_time}")
            }
        }
    }

    fun postLoginResponse(u_id : String, u_pw : String)
    {
        var jsonObject = JSONObject()
        jsonObject.put("id", u_id)
        jsonObject.put("password", u_pw)

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        val postLoginResponse : Call<PostLoginResponse> =
            networkService.postLoginResponse("application/json", gsonObject)
        postLoginResponse.enqueue(object : Callback<PostLoginResponse>{

            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                Log.e("Login failed", t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                if(response.isSuccessful){
                    if(response.body()!!.status == 201) {
                        SharedPreferenceController.setUserToken(applicationContext, response.body()!!.data!!)
                        finish()
                    }
                }
            }
        })
    }
}
