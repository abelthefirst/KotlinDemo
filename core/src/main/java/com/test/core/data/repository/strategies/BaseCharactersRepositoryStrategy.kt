package com.test.core.data.repository.strategies

import com.test.core.data.repository.BreakingBadCharacter

internal abstract class BaseCharactersRepositoryStrategy(
    protected val upstream: CharactersRepositoryStrategy
) : CharactersRepositoryStrategy {

    override fun clearCharacters() {
        (this as? PersistentCharactersRepositoryStrategy)?.clearCharactersImpl()

        upstream.clearCharacters()
    }

    override fun getCharacter(id: Int): BreakingBadCharacter? {
        var character = loadCharacter(id)
        if (character == null) {
            character = upstream.getCharacter(id)?.also {
                if (this is PersistentCharactersRepositoryStrategy) {
                    setCharacter(it)
                }
            }
        }

        return character
    }

    protected abstract fun loadCharacter(id: Int): BreakingBadCharacter?

}
