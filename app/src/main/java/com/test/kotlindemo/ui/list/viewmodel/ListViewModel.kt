package com.test.kotlindemo.ui.list.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.test.core.data.repository.CharactersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListViewModel(charactersRepository: CharactersRepository, context: Context, private val navHostController: NavHostController) : ViewModel() {

    val list = MutableLiveData<List<ListItemViewModel>>()

    private val disposable: Disposable

    init {
        disposable = charactersRepository.getCharacters()
            .map { it.map { breakingBadCharacter -> ListItemViewModel(breakingBadCharacter, navHostController) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.value = it
            }, {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }

    override fun onCleared() {
        disposable.dispose()
    }

}