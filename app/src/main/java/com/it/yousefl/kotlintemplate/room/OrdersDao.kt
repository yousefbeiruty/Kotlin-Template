package com.it.yousefl.kotlintemplate.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrdersDao {
    @Query("SELECT * FROM orders")
    fun getAllCharacters() : LiveData<List<OrderEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<OrderEntity>)

}