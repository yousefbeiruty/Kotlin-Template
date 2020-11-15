package com.it.yousefl.kotlintemplate.room

import androidx.room.Database

@Database(entities = [OrdersCasheEntity::class],version = 1)
abstract class OrderDatabse {
    abstract fun ordergDao():OrdersDao

    companion object{
        val DATABASE_NAME:String="orders_db"
    }
}