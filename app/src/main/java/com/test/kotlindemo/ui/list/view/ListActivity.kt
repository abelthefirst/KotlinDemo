package com.test.kotlindemo.ui.list.view

import android.os.Bundle
import com.test.kotlindemo.R
import com.test.kotlindemo.databinding.ActivityListBinding
import com.test.kotlindemo.ui.common.view.BaseActivity
import com.test.kotlindemo.ui.list.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class ListActivity : BaseActivity<ActivityListBinding, ListViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isRefreshingCharacters.observe(this) {
            if (!it) {
                binding.pullToRefreshView.isRefreshing = false
            }
        }

        binding.pullToRefreshView.setOnRefreshListener {
            viewModel.refreshCharacters()
        }

        binding.listView.adapter = ListAdapter(viewModel)

        viewModel.getCharacters()
    }

    override fun getLayoutId(): Int = R.layout.activity_list

    override fun obtainViewModel(): ListViewModel = getViewModel { parametersOf(navHostController) }

}