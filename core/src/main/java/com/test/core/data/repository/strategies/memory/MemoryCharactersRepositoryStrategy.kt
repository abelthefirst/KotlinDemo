package com.test.core.data.repository.strategies.memory

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy

class MemoryCharactersRepositoryStrategy(private val upstream: CharactersRepositoryStrategy) :
    CharactersRepositoryStrategy {

    private val characterMap: MutableMap<Int, BreakingBadCharacter> = mutableMapOf()

    override fun getCharacter(id: Int): BreakingBadCharacter? {
        if (!isEmpty() && characterMap.containsKey(id)) {
            return characterMap[id]
        }

        return upstream.getCharacter(id)?.also { characterMap[it.id] = it }
    }

    override fun getCharacters(): List<BreakingBadCharacter> {
        if (!isEmpty()) {
            return characterMap.values.sortedBy { it.id }
        }

        return upstream.getCharacters().also { characters -> characterMap.putAll(characters.map { Pair(it.id, it) }) }
    }

    private fun isEmpty(): Boolean = characterMap.isEmpty()

}