package com.test.kotlindemo

import android.app.Application
import android.widget.Toast
import androidx.navigation.NavHostController
import com.test.core.data.repository.CharactersRepository
import com.test.core.data.repository.CharactersRepositoryImpl
import com.test.kotlindemo.data.api.RetrofitBreakingBadApiFactory
import com.test.kotlindemo.data.localstorage.BreakingBadCharacterEntityMapper
import com.test.kotlindemo.data.localstorage.LocalStorageCharactersServiceImpl
import com.test.kotlindemo.data.network.NetworkCharactersServiceImpl
import com.test.kotlindemo.data.network.RetrofitBreakingBadCharacterMapper
import com.test.kotlindemo.data.room.BreakingBadCharactersRoomDatabase
import com.test.kotlindemo.ui.item.viewmodel.ItemViewModel
import com.test.kotlindemo.ui.list.viewmodel.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    private val viewModelModule = module {

        val throwablePresenter: (Throwable) -> Unit = { throwable ->
            Toast.makeText(applicationContext, throwable.message, Toast.LENGTH_SHORT).show()
        }

        viewModel { (navHostController: NavHostController) ->
            ListViewModel(
                throwablePresenter,
                get(),
                navHostController
            )
        }

        viewModel { (id: Int) ->
            ItemViewModel(
                throwablePresenter,
                get(),
                id
            )
        }

    }

    private val repositoryModule = module {

        single<CharactersRepository> { CharactersRepositoryImpl(
            LocalStorageCharactersServiceImpl(
                BreakingBadCharactersRoomDatabase.get(get()),
                BreakingBadCharacterEntityMapper()
            ),
            NetworkCharactersServiceImpl(
                RetrofitBreakingBadApiFactory().build(),
                RetrofitBreakingBadCharacterMapper())
        ) }

    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }

}