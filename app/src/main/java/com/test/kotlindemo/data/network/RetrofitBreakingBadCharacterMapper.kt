package com.test.kotlindemo.data.network

import com.test.kotlindemo.data.api.RetrofitBreakingBadCharacter
import com.test.kotlindemo.data.common.Mapper
import com.test.core.data.repository.BreakingBadCharacter

class RetrofitBreakingBadCharacterMapper : Mapper<RetrofitBreakingBadCharacter, BreakingBadCharacter> {

    override fun map(from: RetrofitBreakingBadCharacter): BreakingBadCharacter = BreakingBadCharacter(
        from.birthday,
        from.id,
        from.image,
        from.name,
        from.nickname,
        from.status)

}