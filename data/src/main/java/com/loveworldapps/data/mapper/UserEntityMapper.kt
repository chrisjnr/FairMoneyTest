package com.loveworldapps.data.mapper

import androidx.paging.DataSource
import com.loveworldapps.data.mapper.entity.UserEntity
import com.loveworldapps.domain.model.User

/**
 * Created by manuelchris-ogar on 22/01/2021.
 */
class UserEntityMapper : EntityMapper<User, UserEntity>{
    override fun mapFromEntity(entity: UserEntity): User {
        return with(entity) {
            User(
                id = this.id,
                email = this.email,
                title = this.title,
                picture = this.picture,
                firstName = this.firstName,
                lastName = this.lastName
            )
        }
    }

    override fun mapToEntity(model: User): UserEntity {
        return with(model) {
            UserEntity(
                id = this.id,
                email = this.email,
                title = this.title,
                picture = this.picture,
                firstName = this.firstName,
                lastName = this.lastName
            )
        }
    }



}