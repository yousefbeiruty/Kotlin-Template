package com.it.yousefl.kotlintemplate.utils

interface EntityMapper<Entity,DomainModel> {

    fun mapFromEntity(etity:Entity):DomainModel

    fun mapToEntity(domainModel: DomainModel):Entity
}