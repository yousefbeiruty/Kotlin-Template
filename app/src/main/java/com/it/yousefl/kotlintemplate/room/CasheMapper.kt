package com.it.yousefl.kotlintemplate.room

import com.it.yousefl.kotlintemplate.models.Orders
import com.it.yousefl.kotlintemplate.utils.EntityMapper
import javax.inject.Inject

class CasheMapper @Inject
constructor(): EntityMapper<OrdersCasheEntity, Orders> {
    override fun mapFromEntity(etity: OrdersCasheEntity): Orders {
        return Orders(companyid = etity.companyid,billno = etity.billno,billdate = etity.billdate
            ,mobile = etity.mobile,address = etity.address,longitude=etity.longitude,
       latitude = etity.latitude ,status = etity.status)
    }

    override fun mapToEntity(domainModel: Orders): OrdersCasheEntity {
        return  OrdersCasheEntity(companyid = domainModel.companyid,billno = domainModel.billno,billdate = domainModel.billdate
            ,mobile = domainModel.mobile,address = domainModel.address,longitude=domainModel.longitude,
            latitude = domainModel.latitude ,status = domainModel.status)
    }

    fun mapFromEntityList(entities:List<OrdersCasheEntity>):List<Orders>{
        return entities.map { mapFromEntity(it)}
    }
}