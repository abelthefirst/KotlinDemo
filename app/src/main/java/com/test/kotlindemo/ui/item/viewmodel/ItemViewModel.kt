package com.test.kotlindemo.ui.item.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ItemViewModel(charactersRepository: CharactersRepository, context: Context, id: Int) : ViewModel() {

    val birthday = MutableLiveData<String>()

    val image = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val nickname = MutableLiveData<String>()

    val status = MutableLiveData<String>()

    private val disposable: Disposable

    init {
        disposable = charactersRepository.getCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleNext(it)
            }, {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }

    override fun onCleared() {
        disposable.dispose()
    }

    private fun handleNext(breakingBadCharacter: BreakingBadCharacter?) {
        breakingBadCharacter?.let {
            birthday.value = "Birthday: ${breakingBadCharacter.birthday}"
            image.value = breakingBadCharacter.image
            name.value = breakingBadCharacter.name
            nickname.value = "Nickname: ${breakingBadCharacter.nickname}"
            status.value = "Status: ${breakingBadCharacter.status}"
        }
    }

}