package com.example.myapplicationchat.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationchat.util.DiffUtilAdapter

abstract class BaseAdapter<T>(
    var _items: List<T>,
    private val listener: BaseInteractionListener? = null,
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    abstract var layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val current = _items[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.apply {
                    setVariable(BR.items, current)
                    setVariable(BR.listener, listener)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun setItems(view: RecyclerView,newItems: List<T>) {
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilAdapter(_items, newItems)
        { oldItemList, newItemList ->
            areItemSame(
                oldItemList,
                newItemList
            )

        })
        _items = newItems

        diffUtilResult.dispatchUpdatesTo(this)
    }

    abstract fun areItemSame(oldItem: T, newItem: T): Boolean

    override fun getItemCount(): Int = _items.size

    val items get() = _items

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(bindings: ViewDataBinding) :
        RecyclerView.ViewHolder(bindings.root)
}
