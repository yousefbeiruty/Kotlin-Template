package com.it.yousefl.kotlintemplate.network

import com.it.yousefl.kotlintemplate.models.Orders
import com.it.yousefl.kotlintemplate.room.OrderEntity
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MainApi {

//    @GET
//    fun getOrders(@Url url: String?): Flowable<List<Orders?>?>?

    @GET
    suspend fun getOrders(@Url url: String) : Response<List<OrderEntity>>
}