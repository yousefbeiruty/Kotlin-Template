package com.it.yousefl.kotlintemplate.repository

import com.it.yousefl.kotlintemplate.models.LoginModel
import com.it.yousefl.kotlintemplate.models.Orders
import com.it.yousefl.kotlintemplate.network.MainApi
import com.it.yousefl.kotlintemplate.room.CasheMapper
import com.it.yousefl.kotlintemplate.room.OrdersDao
import com.it.yousefl.kotlintemplate.utils.AuthResource
import com.it.yousefl.kotlintemplate.utils.NetworkMapper
import com.it.yousefl.kotlintemplate.utils.NetworkOrdersMapper
import com.it.yousefl.kotlintemplate.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository constructor(
    private val orderDao: OrdersDao,
    private val blogRetrofit: MainApi,
    private val cacheMapper: CasheMapper,
    private val networkMapper: NetworkOrdersMapper
){
    suspend fun getBlogs(): Flow<Resource<List<Orders>?>> = flow {
        emit(Resource.loading(null as List<Orders>?))
        delay(1000)
        try{
            val url="get_bill_status.php?companyid=1&status=delivered"
            val networkBlogs = blogRetrofit.getOrders(url)
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                orderDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = orderDao.get()


            emit(Resource.success(cacheMapper.mapFromEntityList(cachedBlogs)))

        }catch (e: Exception){
            emit(Resource.error("Something went wrong $e", null as List<Orders>?))
        }
    }
}