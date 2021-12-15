package com.example.myapplicationchat.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapplicationchat.util.Constant
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ChatRepository {

    private val firebase = FirebaseFirestore.getInstance()
    private val channel = firebase.collection(Constant.COLLECTION_NAME)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTime(): String {
        return DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm.ss")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
    }

    fun addDataToFirebase(name: String?, message: String?) {
        channel.document().set(ChatInfo(
            name = name,
            massage = message,
            date = getCurrentTime()
        ))
    }

    @ExperimentalCoroutinesApi
    fun getFromFirebase(): Flow<State<List<ChatInfo>>> {
        return callbackFlow {
            val snapshotListener =
                firebase.collection(Constant.COLLECTION_NAME).addSnapshotListener { snapshot, e ->
                    val response = if (snapshot != null) {
                        val message = snapshot.toObjects(ChatInfo::class.java)
                        State.Success(message)
                    } else {
                        State.Error(e?.message!!)
                    }
                    trySend(response).isSuccess
                }
            awaitClose {
                snapshotListener.remove()
            }
        }
    }
}