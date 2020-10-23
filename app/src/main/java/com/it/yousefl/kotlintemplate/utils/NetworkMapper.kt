package com.it.yousefl.kotlintemplate.utils

import com.it.yousefl.kotlintemplate.models.LoginModel
import com.it.yousefl.kotlintemplate.network.LoginNetworkEntity
import javax.inject.Inject

class NetworkMapper
@Inject constructor() :EntityMapper<LoginNetworkEntity,LoginModel> {
    override fun mapFromEntity(etity: LoginNetworkEntity): LoginModel {
        return LoginModel(id=etity.id)
    }

    override fun mapToEntity(domainModel: LoginModel): LoginNetworkEntity {
        return LoginNetworkEntity(id=domainModel.id)
    }


}