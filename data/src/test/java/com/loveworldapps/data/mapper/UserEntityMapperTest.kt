package com.loveworldapps.data.mapper

import com.loveworldapps.data.DummyData
import com.loveworldapps.data.mapper.entity.UserEntity
import org.junit.Test
import com.google.common.truth.Truth.assertThat


/**
 * Created by manuelchris-ogar on 22/01/2021.
 */
class UserEntityMapperTest {

    private val userEntityMapper = UserEntityMapper()

    @Test
    fun `check that mapFromModel returns correct data`() {

        val user = DummyData.DummyUser

        val userEntity: UserEntity = userEntityMapper.mapToEntity(model = user)
        assertThat(user.email).isEqualTo(userEntity.email)
        assertThat(user.firstName).isEqualTo(userEntity.firstName)
        assertThat(user.lastName).isEqualTo(userEntity.lastName)
        assertThat(user.title).isEqualTo(userEntity.title)
        assertThat(user.picture).isEqualTo(userEntity.picture)



    }
}