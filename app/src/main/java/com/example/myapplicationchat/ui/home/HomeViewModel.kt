package com.example.myapplicationchat.ui.home

import android.view.KeyboardShortcutInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplicationchat.model.ChatInfo
import com.example.myapplicationchat.model.ChatRepository
import com.example.myapplicationchat.model.State
import com.example.myapplicationchat.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class HomeViewModel : BaseViewModel() {

    private val repository = ChatRepository()
    val inputName = MutableStateFlow<String?>(null)
    val inputMessage = MutableStateFlow<String?>(null)
    val showData = repository.getFromFirebase().asLiveData(Dispatchers.IO)

    fun saveDataInFirebase() {
        if (inputName.value != null && inputMessage.value != null) {
            repository.addDataToFirebase(name = inputName.value, message = inputMessage.value)
        }
    }

}