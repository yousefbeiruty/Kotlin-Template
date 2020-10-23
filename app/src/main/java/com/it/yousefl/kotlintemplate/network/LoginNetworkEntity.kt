package com.it.yousefl.kotlintemplate.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginNetworkEntity(
    @Expose
    @SerializedName("id") val id: Int
)