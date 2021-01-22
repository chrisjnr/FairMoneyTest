package com.loveworldapps.data.mapper

import com.loveworldapps.domain.model.base.Model

/**
 * Created by manuelchris-ogar on 21/01/2021.
 */
open interface EntityMapper<D : Model?, E> {
    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E

    fun mapFromEntityList(entities: List<E>): List<D> {
        return entities.mapTo(mutableListOf(), ::mapFromEntity)
    }

    fun mapFromDomainList(domainModels: List<D>): List<E> {
        return domainModels.mapTo(mutableListOf(), ::mapToEntity)
    }
}