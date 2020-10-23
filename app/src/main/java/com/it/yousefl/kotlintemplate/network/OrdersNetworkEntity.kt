package com.it.yousefl.kotlintemplate.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrdersNetworkEntity(
    @Expose
    @SerializedName("billno")
    private var billno: Int? = null,
    @SerializedName("billdate")
    @Expose
    private val billdate: String? = null,

    @SerializedName("mobile")
    @Expose
    private val mobile: String? = null,

    @SerializedName("longitude")
    @Expose
    private val longitude: Double? = null,

    @SerializedName("latitude")
    @Expose
    private val latitude: Double? = null,

    @SerializedName("address")
    @Expose
    private val address: String? = null,

    @SerializedName("status")
    @Expose
    private val status: String? = null,

    @SerializedName("companyid")
    @Expose
    private val companyid: Int? = null
)
