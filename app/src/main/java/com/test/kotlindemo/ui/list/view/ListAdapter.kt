package com.test.kotlindemo.ui.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.kotlindemo.databinding.LayoutItemBinding
import com.test.kotlindemo.ui.list.viewmodel.ListItemViewModel

@BindingAdapter("list")
fun setList(recyclerView: RecyclerView, list: List<ListItemViewModel>?) {
    val adapter = recyclerView.adapter as? com.test.kotlindemo.ui.list.view.ListAdapter ?: ListAdapter().also { recyclerView.adapter = it }
    adapter.submitList(list)
}

class ListAdapter : ListAdapter<ListItemViewModel, com.test.kotlindemo.ui.list.view.ListAdapter.ListItemViewHolder>(object: DiffUtil.ItemCallback<ListItemViewModel>() {

    override fun areContentsTheSame(oldItem: ListItemViewModel, newItem: ListItemViewModel) = oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: ListItemViewModel, newItem: ListItemViewModel) = areContentsTheSame(oldItem, newItem)

}) {

    class ListItemViewHolder(private val layoutItemBinding: LayoutItemBinding) : RecyclerView.ViewHolder(layoutItemBinding.root) {

        fun bindViewModel(viewModel: ListItemViewModel) {
            layoutItemBinding.viewModel = viewModel
        }

    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindViewModel(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemBinding.inflate(layoutInflater, parent, false)

        return ListItemViewHolder(binding)
    }

}