package com.test.kotlindemo.ui.common.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NoOpNavigator
import com.test.kotlindemo.BR
import com.test.kotlindemo.R

abstract class BaseActivity<B: ViewDataBinding, VM: ViewModel> : AppCompatActivity() {

    protected var binding: B? = null

    protected var navHostController: NavHostController? = null

    protected var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        navHostController = initNavigation()

        super.onCreate(savedInstanceState)

        viewModel = obtainViewModel()
        binding = createBinding()
        binding?.lifecycleOwner = this
        binding?.setVariable(BR.viewModel, viewModel)
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun obtainViewModel(): VM

    private fun createBinding(): B {
        return DataBindingUtil.setContentView(this, getLayoutId())
    }

    private fun initNavigation(): NavHostController {
        val navHostController = NavHostController(this)
        navHostController.navigatorProvider.addNavigator(NoOpNavigator())
        navHostController.setLifecycleOwner(this)
        navHostController.setGraph(R.navigation.nav_graph)

        return navHostController
    }

}