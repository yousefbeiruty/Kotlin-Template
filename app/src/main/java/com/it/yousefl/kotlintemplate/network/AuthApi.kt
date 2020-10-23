package com.it.yousefl.kotlintemplate.network

import com.it.yousefl.kotlintemplate.models.LoginModel
import io.reactivex.Flowable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded //  @Headers("Content-Type: application/json")
    @POST("shop_login.php")
    suspend fun login(@FieldMap options: Map<String, String>):LoginNetworkEntity

}