package com.test.core.data.repository.strategies

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.BreakingBadCharacterListResult

internal abstract class NonPaginatedCharactersRepositoryStrategy(
    upstream: CharactersRepositoryStrategy = TerminalCharactersRepositoryStrategy
) : BaseCharactersRepositoryStrategy(upstream) {

    override fun getCharacters(): BreakingBadCharacterListResult {
        val characters = loadCharacters()
        return if (characters.isEmpty()) {
            upstream.getCharacters().also {
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

    override fun getCharacters(offset: Int) = upstream.getCharacters(offset).also {
        if (this is PersistentCharactersRepositoryStrategy) {
            setCharacters(it.characters)
        }
    }

    protected abstract fun loadCharacters(): List<BreakingBadCharacter>

}
