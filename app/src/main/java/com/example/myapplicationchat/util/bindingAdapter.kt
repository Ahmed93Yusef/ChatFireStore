package com.example.myapplicationchat.util

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationchat.ui.base.BaseAdapter
import com.example.myapplicationchat.util.Constant.TAG

@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerView(items: List<T>?) {
    Log.i(TAG, "setRecyclerView: $items")
    if (items != null) {
        (this.adapter as BaseAdapter<T>?)?.setItems(this,items)
    } else {
        (this.adapter as BaseAdapter<T>?)?.setItems(this,emptyList())
    }
}

