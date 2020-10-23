package com.it.yousefl.kotlintemplate.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Orders(
    private val billno: Int,
    private val billdate: String,
    private val mobile: String,
    private val longitude: Double,
    private val latitude: Double,
    private val address: String,
    private val status: String,
    private val companyid: Int,
)