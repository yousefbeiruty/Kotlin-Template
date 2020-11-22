package com.it.yousefl.kotlintemplate.repository

import com.it.yousefl.kotlintemplate.remote.OrdersRemoteDataSource
import com.it.yousefl.kotlintemplate.room.OrdersDao
import com.it.yousefl.kotlintemplate.utils.performGetOperation
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: OrdersRemoteDataSource,
    private val localDataSource: OrdersDao
) {

//    fun getCharacter(id: Int) = performGetOperation(
//       // databaseQuery = { localDataSource.getCharacter(id) },
//        networkCall = { remoteDataSource.getCharacter(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )

    fun getOrders(id:Int) = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getOrders(id) },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}