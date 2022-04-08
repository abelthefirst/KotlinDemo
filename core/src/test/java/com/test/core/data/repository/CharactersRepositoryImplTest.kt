package com.test.core.data.repository

import com.test.core.service.LocalStorageCharactersService
import com.test.core.service.NetworkCharactersService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CharactersRepositoryImplTest {

    private val localStorageService: LocalStorageCharactersService = Mockito.mock(LocalStorageCharactersService::class.java)

    private val networkCharactersService: NetworkCharactersService = Mockito.mock(NetworkCharactersService::class.java)

    private val charactersRepositoryImpl = CharactersRepositoryImpl(localStorageService, networkCharactersService)

    @Test
    fun test_getCharacter_fromNetwork() = runBlocking {
        val characterEntity = createCharacter()
        Mockito.`when`(localStorageService.getCharacter(characterEntity.id)).thenReturn(null)
        Mockito.`when`(networkCharactersService.getCharacter(characterEntity.id)).thenReturn(characterEntity)

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character)

        Mockito.verify(localStorageService).setCharacter(characterEntity)
    }

    @Test
    fun test_getCharacter_fromLocalStorage() = runBlocking {
        val characterEntity = createCharacter()
        Mockito.`when`(localStorageService.getCharacter(characterEntity.id)).thenReturn(characterEntity)

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character)

        Mockito.verifyNoInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacter_fromMemory() = runBlocking {
        val characterEntity = createCharacter()
        Mockito.`when`(localStorageService.getCharacter(characterEntity.id)).thenReturn(characterEntity)
        charactersRepositoryImpl.getCharacter(characterEntity.id)
        Mockito.reset(localStorageService)

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character)

        Mockito.verifyNoInteractions(localStorageService)
        Mockito.verifyNoInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacters_fromNetwork() = runBlocking {
        val networkCharacters = listOf(createCharacter())
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(emptyList<BreakingBadCharacter>())
        Mockito.`when`(networkCharactersService.getCharacters()).thenReturn(networkCharacters)

        val characters = charactersRepositoryImpl.getCharacters()

        Assert.assertEquals(networkCharacters, characters)

        Mockito.verify(localStorageService).setCharacters(networkCharacters)
    }

    @Test
    fun test_getCharacters_fromLocalStorage() = runBlocking {
        val localStorageCharacters = listOf(createCharacter())
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(localStorageCharacters)

        val characters = charactersRepositoryImpl.getCharacters()

        Assert.assertEquals(localStorageCharacters, characters)

        Mockito.verifyNoInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacters_fromMemory() = runBlocking {
        val localStorageCharacters = listOf(createCharacter())
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(localStorageCharacters)
        charactersRepositoryImpl.getCharacters()
        Mockito.reset(localStorageService)

        val characters = charactersRepositoryImpl.getCharacters()

        Assert.assertEquals(localStorageCharacters, characters)

        Mockito.verifyNoInteractions(localStorageService)
        Mockito.verifyNoInteractions(networkCharactersService)
    }

    @Test
    fun test_getCharacter_after_getCharacters() = runBlocking {
        val characterEntity = createCharacter()
        val localStorageCharacters = listOf(characterEntity)
        Mockito.`when`(localStorageService.getCharacters()).thenReturn(localStorageCharacters)
        charactersRepositoryImpl.getCharacters()
        Mockito.reset(localStorageService)

        val character = charactersRepositoryImpl.getCharacter(characterEntity.id)

        Assert.assertEquals(characterEntity, character)

        Mockito.verifyNoInteractions(localStorageService)
        Mockito.verifyNoInteractions(networkCharactersService)
    }

    private fun createCharacter() = BreakingBadCharacter("BIRTHDAY", 0, "IMAGE", "NAME", "NICKNAME", "STATUS")

}