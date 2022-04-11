package com.test.core.data.repository

internal abstract class BaseCharactersRepositoryStrategy : CharactersRepositoryStrategy {

    protected abstract val upstream: CharactersRepositoryStrategy?

    private var hasMore: Boolean = true

    override fun getCharacter(id: Int): BreakingBadCharacter? {
        var character = loadCharacter(id)
        if (character == null) {
            character = fetchCharacterFromTheUpstream(id)
        }

        return character
    }

    override fun getCharacters(): BreakingBadCharacterListResult {
        var breakingBadCharacterListResult = BreakingBadCharacterListResult(
            characters = loadCharacters(),
            hasMore = hasMore
        )
        if (breakingBadCharacterListResult.characters.isEmpty()) {
            breakingBadCharacterListResult = fetchCharactersFromTheUpstream()
        }

        return breakingBadCharacterListResult
    }

    protected abstract fun loadCharacter(id: Int): BreakingBadCharacter?

    protected abstract fun loadCharacters(): List<BreakingBadCharacter>

    protected abstract fun setCharacter(character: BreakingBadCharacter)

    protected abstract fun setCharacters(characters: List<BreakingBadCharacter>)

    private fun fetchCharacterFromTheUpstream(id: Int) = upstream?.getCharacter(id)?.also {
        setCharacter(it)
    }

    private fun fetchCharactersFromTheUpstream() = upstream?.getCharacters()?.also {
        setCharacters(it.characters)
        hasMore = it.hasMore
    } ?:BreakingBadCharacterListResult(characters = emptyList(), hasMore = hasMore)

}
