package com.it.yousefl.kotlintemplate.remote

import com.it.yousefl.kotlintemplate.network.MainApi
import com.it.yousefl.kotlintemplate.utils.Constants
import javax.inject.Inject

class OrdersRemoteDataSource @Inject constructor(
    private val mainApi: MainApi
): BaseDataSource() {

    suspend fun getOrders(comapny_id:Int) = getResult {
        val url: String ="get_bill_status.php?companyid="+comapny_id+"&status=delivered";
        mainApi.getOrders(url)

    }

}