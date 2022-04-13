package com.test.core.data.repository.strategies.network

import com.test.core.data.repository.strategies.PaginatedCharactersRepositoryStrategy
import com.test.core.service.NetworkCharactersService

internal class NetworkCharactersRepositoryStrategy(
    private val networkCharactersService: NetworkCharactersService
) : PaginatedCharactersRepositoryStrategy() {

    override val pageSize = 10

    override fun loadCharacter(id: Int) = networkCharactersService.getCharacter(id)

    override fun loadCharacters(offset: Int, pageSize: Int) = networkCharactersService.getCharacters(
        offset = offset,
        pageSize = pageSize
    )

}
