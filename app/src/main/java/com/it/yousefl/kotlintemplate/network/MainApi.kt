package com.it.yousefl.kotlintemplate.network

import com.it.yousefl.kotlintemplate.models.Orders
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Url

interface MainApi {

    @GET
    suspend fun getOrders(@Url url: String?): List<OrdersNetworkEntity>
}