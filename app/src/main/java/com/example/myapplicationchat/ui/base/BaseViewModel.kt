package com.example.myapplicationchat.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.myapplicationchat.util.EventHandled

abstract class BaseViewModel : ViewModel() {
    private val _navigationLiveData = MutableLiveData<EventHandled<NavDirections>>()
    val navigationLiveData: LiveData<EventHandled<NavDirections>> = _navigationLiveData

    fun navigate(direction: NavDirections) {
        _navigationLiveData.postValue(EventHandled(direction))
    }
}
