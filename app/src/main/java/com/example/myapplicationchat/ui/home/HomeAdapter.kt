package com.example.myapplicationchat.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationchat.R
import com.example.myapplicationchat.model.ChatInfo
import com.example.myapplicationchat.ui.base.BaseAdapter

class HomeAdapter(item: List<ChatInfo>, listener: HomeInteractionListener): BaseAdapter<ChatInfo>(item,listener) {
    override var layoutId = R.layout.message_item

    override fun areItemSame(oldItem: ChatInfo, newItem: ChatInfo) = true

    override fun setItems(view: RecyclerView, newItems: List<ChatInfo>) {
        super.setItems(view, newItems)
        _items = newItems.sortedBy { it.date }
        view.scrollToPosition(_items.lastIndex)
    }
}