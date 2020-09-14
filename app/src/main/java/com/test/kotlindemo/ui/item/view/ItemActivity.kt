package com.test.kotlindemo.ui.item.view

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.navArgs
import com.test.kotlindemo.R
import com.test.kotlindemo.databinding.ActivityItemBinding
import com.test.kotlindemo.ui.common.view.BaseActivity
import com.test.kotlindemo.ui.item.viewmodel.ItemViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class ItemActivity : BaseActivity<ActivityItemBinding, ItemViewModel>() {

    private val itemActivityArgs: ItemActivityArgs by navArgs()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int = R.layout.activity_item

    override fun obtainViewModel(): ItemViewModel = getViewModel { parametersOf(itemActivityArgs.itemId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
    }

    private fun initActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.title = null
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

}