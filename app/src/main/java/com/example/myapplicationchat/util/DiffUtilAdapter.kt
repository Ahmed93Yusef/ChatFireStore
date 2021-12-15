package com.example.myapplicationchat.util

import androidx.recyclerview.widget.DiffUtil

class DiffUtilAdapter<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val checkIfSame: (oldItemList: T, newItemList: T) -> Boolean
    ) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        checkIfSame(oldList[oldItemPosition], newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}