package com.test.core.data.repository.strategies.memory

import com.test.core.data.repository.CharactersRepositoryStrategyWithAnUpstream
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy

internal class MemoryCharactersRepositoryStrategy(override val upstream: CharactersRepositoryStrategy) :
    CharactersRepositoryStrategyWithAnUpstream() {

    private val characterMap: MutableMap<Int, BreakingBadCharacter> = mutableMapOf()

    override fun clearCharactersImpl() = characterMap.clear()

    override fun loadCharacter(id: Int) = characterMap[id]

    override fun loadCharacters() = characterMap.values.sortedBy { it.id }

    override fun setCharacter(character: BreakingBadCharacter) {
        characterMap[character.id] = character
    }

    override fun setCharacters(characters: List<BreakingBadCharacter>) = characterMap
        .putAll(characters.map { Pair(it.id, it) })

}
