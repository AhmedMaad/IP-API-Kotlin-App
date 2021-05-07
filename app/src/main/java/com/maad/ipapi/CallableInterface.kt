package com.maad.ipapi

import retrofit2.Call
import retrofit2.http.GET

    interface CallableInterface {

        @GET("/json")
        fun getData(): Call<IPModel>

    }