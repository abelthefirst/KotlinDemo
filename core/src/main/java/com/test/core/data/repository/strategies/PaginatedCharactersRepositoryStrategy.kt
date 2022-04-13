package com.test.core.data.repository.strategies

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.BreakingBadCharacterListResult

internal abstract class PaginatedCharactersRepositoryStrategy(
    upstream: CharactersRepositoryStrategy = TerminalCharactersRepositoryStrategy
) : BaseCharactersRepositoryStrategy(upstream) {

    protected abstract val pageSize: Int

    override fun getCharacters() = getCharacters(0)

    override fun getCharacters(offset: Int): BreakingBadCharacterListResult {
        val characters = loadCharacters(offset, pageSize)
        return if (characters.isEmpty()) {
            upstream.getCharacters(offset).also {
                if (this is PersistentCharactersRepositoryStrategy) {
                    setCharacters(it.characters)
                }
            }
        } else {
            BreakingBadCharacterListResult(
                characters = characters,
                hasMore = true
            )
        }
    }

    protected abstract fun loadCharacters(offset: Int, pageSize: Int): List<BreakingBadCharacter>

}
