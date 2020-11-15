package com.it.yousefl.kotlintemplate.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Orders(
    var billno: Int,
    var billdate: String,
    var mobile: String,
    var longitude: Double,
    var latitude: Double,
    var address: String,
    var status: String,
    var companyid: Int,
)