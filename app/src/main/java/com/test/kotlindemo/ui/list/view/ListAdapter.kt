package com.test.kotlindemo.ui.list.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.kotlindemo.R
import com.test.kotlindemo.databinding.LayoutItemBinding
import com.test.kotlindemo.ui.list.viewmodel.AbstractListItemViewModel
import com.test.kotlindemo.ui.list.viewmodel.ListViewModel

@BindingAdapter("list")
fun setList(recyclerView: RecyclerView, list: List<AbstractListItemViewModel>?) {
    (recyclerView.adapter as? ListAdapter)?.submitList(list)
}

class ListAdapter(val listViewModel: ListViewModel) : androidx.recyclerview.widget.ListAdapter<AbstractListItemViewModel, ListAdapter.AbstractListItemViewHolder>(object: DiffUtil.ItemCallback<AbstractListItemViewModel>() {

    override fun areContentsTheSame(oldItem: AbstractListItemViewModel, newItem: AbstractListItemViewModel): Boolean {
        if (oldItem is AbstractListItemViewModel.ListItemViewModel && newItem is AbstractListItemViewModel.ListItemViewModel) {
            return oldItem.id == newItem.id
        }

        return false
    }

    override fun areItemsTheSame(oldItem: AbstractListItemViewModel, newItem: AbstractListItemViewModel) = areContentsTheSame(oldItem, newItem)

}) {

    sealed class AbstractListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        class ListItemViewHolder(private val layoutItemBinding: LayoutItemBinding) :
            AbstractListItemViewHolder(layoutItemBinding.root) {

            fun bindViewModel(viewModel: AbstractListItemViewModel.ListItemViewModel) {
                layoutItemBinding.viewModel = viewModel
            }

        }
        class LoadingListItemViewHolder(itemView: View) : AbstractListItemViewHolder(itemView)
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position) is AbstractListItemViewModel.LoadingListItemViewModel) {
            return VIEW_TYPE_LOADING_LIST_ITEM
        }

        return VIEW_TYPE_LIST_ITEM
    }

    override fun onBindViewHolder(holder: AbstractListItemViewHolder, position: Int) {
        when (holder) {
            is AbstractListItemViewHolder.ListItemViewHolder -> {
                holder.bindViewModel(getItem(position) as AbstractListItemViewModel.ListItemViewModel)
            }
            is AbstractListItemViewHolder.LoadingListItemViewHolder -> {
                Log.d("Debug", "onBindViewHolder LoadingListItemViewHolder")

                listViewModel.getMoreCharacters()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOADING_LIST_ITEM) {
            val itemView = layoutInflater.inflate(R.layout.layout_loading_item, parent, false)

            return AbstractListItemViewHolder.LoadingListItemViewHolder(itemView)
        }

        val binding = LayoutItemBinding.inflate(layoutInflater, parent, false)

        return AbstractListItemViewHolder.ListItemViewHolder(binding)
    }

    private companion object {

        private const val VIEW_TYPE_LIST_ITEM = 0
        private const val VIEW_TYPE_LOADING_LIST_ITEM = 1

    }

}