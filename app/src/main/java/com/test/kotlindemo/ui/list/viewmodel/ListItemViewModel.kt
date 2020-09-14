package com.test.kotlindemo.ui.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.test.core.data.repository.BreakingBadCharacter
import com.test.kotlindemo.ui.list.view.ListActivityDirections

class ListItemViewModel(breakingBadCharacter: BreakingBadCharacter, private val navHostController: NavHostController) : ViewModel() {

    val id = breakingBadCharacter.id

    val image = breakingBadCharacter.image

    val name = breakingBadCharacter.name

    val nickname = breakingBadCharacter.nickname

    fun onItemTap() {
        navHostController.navigate(ListActivityDirections.navigateToItem(id))
    }

}