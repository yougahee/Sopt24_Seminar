package com.example.sopt24_seminar.DB

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {

    val MY_ACCOUNT = "unique_string"

    fun setUserToken(ctx: Context, time : String) {
        val preference : SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = preference.edit()
        editor.putString("u_token", time)
        editor.commit()
    }

    fun getUserToken(ctx : Context) :String {
        val preference : SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return preference.getString("u_token", "")
    }

    fun clearUserToken(ctx : Context) {
        val preference : SharedPreferences = ctx.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor  = preference.edit()
        //sharedpreference의 모든!! 데이터가 삭제된다.
        editor.clear()
        editor.commit()
    }


}