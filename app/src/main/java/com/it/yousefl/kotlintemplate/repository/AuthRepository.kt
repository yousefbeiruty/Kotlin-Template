package com.it.yousefl.kotlintemplate.repository

import com.it.yousefl.kotlintemplate.models.LoginModel
import com.it.yousefl.kotlintemplate.network.AuthApi
import com.it.yousefl.kotlintemplate.utils.AuthResource
import com.it.yousefl.kotlintemplate.utils.DataState
import com.it.yousefl.kotlintemplate.utils.NetworkMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class AuthRepository
constructor(
    private val authApi: AuthApi,
    private val networkMapper: NetworkMapper
){

    suspend fun login(username: String, password: String):  Flow<AuthResource<LoginModel>> =
        flow {
            emit(AuthResource.loading(null as LoginModel?))
            delay(1000)
            try{
                val hashMap = HashMap<String, String>()
                hashMap["username"] = username
                hashMap["password"] = password
                val networkLogins = authApi.login(hashMap)
                val login = networkMapper.mapFromEntity(networkLogins)
                emit(AuthResource.authenticated(login))
            }catch (e: Exception){

                emit(AuthResource.error("Cloud not authenticate $e", null as LoginModel?))
            }
        } as Flow<AuthResource<LoginModel>>
}