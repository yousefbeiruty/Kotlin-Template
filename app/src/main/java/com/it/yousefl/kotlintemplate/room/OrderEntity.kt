package com.it.yousefl.kotlintemplate.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "billno")
    @Expose
    @SerializedName("billno")
    var billno: Int,

    @SerializedName("companyid")
    @Expose
    @ColumnInfo(name = "companyid")
    var companyid: Int,

    @SerializedName("billdate")
    @Expose
    @ColumnInfo(name = "billdate")
    var billdate: String,



    @SerializedName("mobile")
    @Expose
    @ColumnInfo(name = "mobile")
    var mobile: String,


    @SerializedName("address")
    @Expose
    @ColumnInfo(name = "address")
    var address: String,


    @SerializedName("longitude")
    @Expose
    @ColumnInfo(name = "longitude")
    var longitude: Double,


    @SerializedName("latitude")
    @Expose
    @ColumnInfo(name = "latitude")
    var latitude: Double,


    @SerializedName("status")
    @Expose
    @ColumnInfo(name = "status")
    var status: String
)