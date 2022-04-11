package com.test.core.data.repository.strategies.memory

import com.test.core.data.repository.BaseCharactersRepositoryStrategy
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy

internal class MemoryCharactersRepositoryStrategy(override val upstream: CharactersRepositoryStrategy) :
    BaseCharactersRepositoryStrategy() {

    private val characterMap: MutableMap<Int, BreakingBadCharacter> = mutableMapOf()

    override fun loadCharacter(id: Int) = characterMap[id]

    override fun loadCharacters() = characterMap.values.toList()

    override fun setCharacter(character: BreakingBadCharacter) {
        characterMap[character.id] = character
    }

    override fun setCharacters(characters: List<BreakingBadCharacter>) = characterMap
        .putAll(characters.map { Pair(it.id, it) })

}
