package com.example.myapplicationchat.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun LiveData<EventHandled<NavDirections>>.observeEvent(owner: LifecycleOwner, navController: NavController){
    this.observe(owner, { event ->
        event?.getContentIfNotHandle()?.let { command ->
            navController.navigate(command)
        }
    })
}