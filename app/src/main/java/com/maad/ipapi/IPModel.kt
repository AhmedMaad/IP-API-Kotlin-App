package com.maad.ipapi

import com.google.gson.annotations.SerializedName

class IPModel {

    val city: String = ""
    val country: String = ""

    @SerializedName("lat")
    val latitude: Double = 0.0

    @SerializedName("lon")
    val longitude: Double = 0.0

}


