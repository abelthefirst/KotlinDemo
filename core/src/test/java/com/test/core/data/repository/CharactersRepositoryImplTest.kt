package com.test.core.data.repository

import com.test.core.service.LocalStorageCharactersService
import com.test.core.service.NetworkCharactersService
import io.reactivex.Flowable
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CharactersRepositoryImplTest {

    private val localStorageService: LocalStorageCharactersService = Mockito.mock(LocalStorageCharactersService::class.java)

    private val networkCharactersService: NetworkCharactersService = Mockito.mock(NetworkCharactersService::class.java)

    private val charactersRepositoryImpl = CharactersRepositoryImpl(localStorageService, networkCharactersService)

    @Test
    fun test_getCharacter_fromNetwork() {
        val characterEntity = createCharacter()
        Mockito.`when`(localStorageService.getCharacter(characterEntity.id)).thenReturn(Flowable.fromCallable { emptyList<BreakingBadCharacter>() })
        Mockito.`when`(networkCharactersService.getCharacter(characterEntity.id)).thenReturn(Flowable.fromCallable { characterEntity })

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character.blockingFirst())

        Mockito.verify(localStorageService).setCharacter(characterEntity)
    }

    @Test
    fun test_getCharacter_fromLocalStorage() {
        val characterEntity = createCharacter()
        val localStorageCharacters = listOf(characterEntity)
        Mockito.`when`(localStorageService.getCharacter(characterEntity.id)).thenReturn(Flowable.fromCallable { localStorageCharacters })

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character.blockingFirst())

        Mockito.verifyZeroInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacter_fromMemory() {
        val characterEntity = createCharacter()
        val localStorageCharacters = listOf(characterEntity)
        Mockito.`when`(localStorageService.getCharacter(characterEntity.id)).thenReturn(Flowable.fromCallable { localStorageCharacters })
        charactersRepositoryImpl.getCharacter(characterEntity.id).blockingFirst()
        Mockito.reset(localStorageService)

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character.blockingFirst())

        Mockito.verifyZeroInteractions(localStorageService)
        Mockito.verifyZeroInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacters_fromNetwork() {
        val networkCharacters = listOf(createCharacter())
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(Flowable.fromCallable { emptyList<BreakingBadCharacter>() })
        Mockito.`when`(networkCharactersService.getCharacters()).thenReturn(Flowable.fromCallable { networkCharacters })

        val characters = charactersRepositoryImpl.getCharacters()

        Assert.assertEquals(networkCharacters, characters.blockingFirst())

        Mockito.verify(localStorageService).setCharacters(networkCharacters)
    }

    @Test
    fun test_getCharacters_fromLocalStorage() {
        val localStorageCharacters = listOf(createCharacter())
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(Flowable.fromCallable { localStorageCharacters })

        val characters = charactersRepositoryImpl.getCharacters()

        Assert.assertEquals(localStorageCharacters, characters.blockingFirst())

        Mockito.verifyZeroInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacters_fromMemory() {
        val localStorageCharacters = listOf(createCharacter())
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(Flowable.fromCallable { localStorageCharacters })
        charactersRepositoryImpl.getCharacters().blockingFirst()
        Mockito.reset(localStorageService)

        val characters = charactersRepositoryImpl.getCharacters()

        Assert.assertEquals(localStorageCharacters, characters.blockingFirst())

        Mockito.verifyZeroInteractions(localStorageService)
        Mockito.verifyZeroInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacter_after_getCharacters() {
        val characterEntity = createCharacter()
        val localStorageCharacters = listOf(characterEntity)
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(Flowable.fromCallable { localStorageCharacters })
        charactersRepositoryImpl.getCharacters().blockingFirst()
        Mockito.reset(localStorageService)

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character.blockingFirst())

        Mockito.verifyZeroInteractions(localStorageService)
        Mockito.verifyZeroInteractions(networkCharactersService)
    }

    private fun createCharacter() = BreakingBadCharacter("BIRTHDAY", 0, "IMAGE", "NAME", "NICKNAME", "STATUS")

}