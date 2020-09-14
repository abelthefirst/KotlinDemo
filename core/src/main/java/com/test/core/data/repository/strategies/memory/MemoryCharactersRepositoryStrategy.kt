package com.test.core.data.repository.strategies.memory

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy
import io.reactivex.Flowable

class MemoryCharactersRepositoryStrategy(val upstream: CharactersRepositoryStrategy) :
    CharactersRepositoryStrategy {

    private val characterMap: MutableMap<Int, BreakingBadCharacter> = mutableMapOf()

    override fun getCharacter(id: Int): Flowable<BreakingBadCharacter?> {
        if (!isEmpty() && characterMap.containsKey(id)) {
            return flowable { characterMap[id] }
        }

        return upstream.getCharacter(id).doOnNext { it?.let { characterMap.put(it.id, it) } }
    }

    override fun getCharacters(): Flowable<List<BreakingBadCharacter>> {
        if (!isEmpty()) {
            return flowable { characterMap.values.sortedBy { it.id } }
        }

        return upstream.getCharacters().doOnNext { characterMap.putAll(it.map { Pair(it.id, it) }) }
    }

    private fun <T> flowable(callable: () -> T): Flowable<T> {
        return Flowable.fromCallable(callable)
    }

    private fun isEmpty(): Boolean = characterMap.isEmpty()

}