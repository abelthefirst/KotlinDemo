package com.test.kotlindemo.ui.item.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemViewModel(
    throwablePresenter: (Throwable) -> Unit,
    charactersRepository: CharactersRepository,
    id: Int
) : ViewModel() {

    val birthday = MutableLiveData<String>()

    val image = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val nickname = MutableLiveData<String>()

    val status = MutableLiveData<String>()

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            throwablePresenter.invoke(exception)
        }
        viewModelScope.launch(handler) {
            val character = withContext(Dispatchers.IO) {
                charactersRepository
                    .getCharacter(id)
            }
            handleCharacter(character)
        }
    }

    private fun handleCharacter(breakingBadCharacter: BreakingBadCharacter?) {
        breakingBadCharacter?.let {
            birthday.value = "Birthday: ${it.birthday}"
            image.value = it.image
            name.value = it.name
            nickname.value = "Nickname: ${it.nickname}"
            status.value = "Status: ${it.status}"
        }
    }

}