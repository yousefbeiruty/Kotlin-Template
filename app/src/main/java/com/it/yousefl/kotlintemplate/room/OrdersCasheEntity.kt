package com.it.yousefl.kotlintemplate.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "orders")
data class OrdersCasheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "billno")
    var billno: Int,
    @ColumnInfo(name = "companyid")
    var companyid: Int,

    @ColumnInfo(name = "billdate")
    var billdate: String,

    @ColumnInfo(name = "mobile")
    var mobile: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "status")
    var status: String

)
