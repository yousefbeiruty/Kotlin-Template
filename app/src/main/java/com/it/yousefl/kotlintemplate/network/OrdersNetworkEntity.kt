package com.it.yousefl.kotlintemplate.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrdersNetworkEntity(
    @Expose
    @SerializedName("billno")
     var billno: Int ,
    @SerializedName("billdate")
    @Expose
     var billdate: String,

    @SerializedName("mobile")
    @Expose
    var mobile: String,

    @SerializedName("longitude")
    @Expose
    var longitude: Double,

    @SerializedName("latitude")
    @Expose
    var latitude: Double,

    @SerializedName("address")
    @Expose
    var address: String,

    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("companyid")
    @Expose
    var companyid: Int
)
