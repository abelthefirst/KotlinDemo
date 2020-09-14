package com.test.kotlindemo.data.localstorage

import com.test.kotlindemo.data.common.Mapper
import com.test.core.data.repository.BreakingBadCharacter
import com.test.kotlindemo.data.room.BreakingBadCharacterEntity

class BreakingBadCharacterEntityMapper : Mapper<BreakingBadCharacterEntity, BreakingBadCharacter> {

    fun map(from: BreakingBadCharacter): BreakingBadCharacterEntity = BreakingBadCharacterEntity(
        from.birthday,
        from.id,
        from.image,
        from.name,
        from.nickname,
        from.status)

    override fun map(from: BreakingBadCharacterEntity): BreakingBadCharacter = BreakingBadCharacter(
        from.birthday,
        from.id,
        from.image,
        from.name,
        from.nickname,
        from.status)

}