package com.it.yousefl.kotlintemplate.room

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderEntity:OrdersCasheEntity):Long

    @Query("SELECT * FROM orders")
    suspend fun get():List<OrdersCasheEntity>



}