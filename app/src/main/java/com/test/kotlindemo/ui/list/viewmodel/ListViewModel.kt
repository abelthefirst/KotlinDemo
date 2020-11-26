package com.test.kotlindemo.ui.list.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.test.core.data.repository.CharactersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(charactersRepository: CharactersRepository, context: Context, private val navHostController: NavHostController) : ViewModel() {

    val list = MutableLiveData<List<ListItemViewModel>>()

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
        }
        viewModelScope.launch(handler) {
            val characters = withContext(Dispatchers.IO) {
                charactersRepository
                    .getCharacters()
                    .map { breakingBadCharacter ->
                        ListItemViewModel(
                            breakingBadCharacter,
                            navHostController
                        )
                    }
            }
            list.value = characters
        }
    }

}