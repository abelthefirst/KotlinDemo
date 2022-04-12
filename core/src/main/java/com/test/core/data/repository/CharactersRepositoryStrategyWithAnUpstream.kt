package com.test.core.data.repository

internal abstract class CharactersRepositoryStrategyWithAnUpstream : CharactersRepositoryStrategy {

    protected abstract val upstream: CharactersRepositoryStrategy

    override fun clearCharacters() = clearCharactersImpl().also {
        upstream.clearCharacters()
    }

    override fun getCharacter(id: Int): BreakingBadCharacter? {
        var character = loadCharacter(id)
        if (character == null) {
            character = fetchCharacterFromTheUpstream(id)
        }

        return character
    }

    override fun getCharacters(): BreakingBadCharacterListResult {
        val characters = loadCharacters()
        return if (characters.isEmpty()) {
            fetchCharactersFromTheUpstream()
        } else {
            BreakingBadCharacterListResult(
                characters = characters,
                hasMore = true
            )
        }
    }

    override fun getCharacters(offset: Int): BreakingBadCharacterListResult {
        return upstream.getCharacters(offset).also {
            setCharacters(it.characters)
        }
    }

    protected abstract fun clearCharactersImpl()

    protected abstract fun loadCharacter(id: Int): BreakingBadCharacter?

    protected abstract fun loadCharacters(): List<BreakingBadCharacter>

    protected abstract fun setCharacter(character: BreakingBadCharacter)

    protected abstract fun setCharacters(characters: List<BreakingBadCharacter>)

    private fun fetchCharacterFromTheUpstream(id: Int) = upstream.getCharacter(id)?.also {
        setCharacter(it)
    }

    private fun fetchCharactersFromTheUpstream() = upstream.getCharacters().also {
        setCharacters(it.characters)
    }

}
