package com.it.yousefl.kotlintemplate.utils

import com.it.yousefl.kotlintemplate.models.Orders
import com.it.yousefl.kotlintemplate.network.OrdersNetworkEntity
import javax.inject.Inject

class NetworkOrdersMapper
@Inject constructor() :EntityMapper<OrdersNetworkEntity,Orders> {
    override fun mapFromEntity(etity: OrdersNetworkEntity): Orders {
        return Orders(companyid = etity.companyid,billno = etity.billno,billdate = etity.billdate
            ,mobile = etity.mobile,address = etity.address,longitude=etity.longitude,
            latitude = etity.latitude ,status = etity.status)
    }

    override fun mapToEntity(domainModel: Orders): OrdersNetworkEntity {
        return OrdersNetworkEntity(companyid = domainModel.companyid,billno = domainModel.billno,billdate = domainModel.billdate
            ,mobile = domainModel.mobile,address = domainModel.address,longitude=domainModel.longitude,
            latitude = domainModel.latitude ,status = domainModel.status)
    }

    fun mapFromEntityList(entities:List<OrdersNetworkEntity>):List<Orders>{
        return  entities.map { mapFromEntity(it) }
    }
}