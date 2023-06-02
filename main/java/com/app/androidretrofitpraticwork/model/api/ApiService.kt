package com.app.androidretrofitpraticwork.model.api

import com.app.androidretrofitpraticwork.model.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movielist.json")
    fun getUserList():Call<MutableList<UserData>>
}