package com.test.kotlindemo.ui.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.test.core.data.repository.BreakingBadCharacterListResult
import com.test.core.data.repository.CharactersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(
    throwablePresenter: (Throwable) -> Unit,
    private val charactersRepository: CharactersRepository,
    private val navHostController: NavHostController
) : ViewModel() {

    private val _isRefreshingCharacters = MutableLiveData<Boolean>()
    val isRefreshingCharacters: LiveData<Boolean> = _isRefreshingCharacters

    private val _list = MutableLiveData<List<AbstractListItemViewModel>>()
    val list: LiveData<List<AbstractListItemViewModel>> = _list

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _isRefreshingCharacters.value = false

        throwablePresenter.invoke(exception)
    }

    fun getCharacters() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val characters = withContext(Dispatchers.IO) {
                charactersRepository
                    .getCharacters()
                    .toViewModelList()
            }
            _list.value = characters
        }
    }

    fun getMoreCharacters() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val characters = withContext(Dispatchers.IO) {
                charactersRepository
                    .getMoreCharacters()
                    .toViewModelList()
            }
            _list.value = _list.value?.let {
                it.toMutableList().filterIsInstance<AbstractListItemViewModel.ListItemViewModel>() + characters
            }
        }
    }

    fun refreshCharacters() {
        _isRefreshingCharacters.value = true
        viewModelScope.launch(coroutineExceptionHandler) {
            val characters = withContext(Dispatchers.IO) {
                charactersRepository
                    .refreshCharacters()
                    .toViewModelList()
            }
            _list.value = characters
            _isRefreshingCharacters.value = false
        }
    }

    private fun BreakingBadCharacterListResult.toViewModelList(): List<AbstractListItemViewModel> {
        val viewModelList: MutableList<AbstractListItemViewModel> = characters
            .map { breakingBadCharacter ->
                AbstractListItemViewModel.ListItemViewModel(
                    breakingBadCharacter,
                    navHostController
                )
            }
            .toMutableList()
        if (hasMore) {
            viewModelList.add(AbstractListItemViewModel.LoadingListItemViewModel)
        }

        return viewModelList
    }

}
