package com.example.phoneforfilm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.phoneforfilm.data.AppDatabase
import com.example.phoneforfilm.model.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    init {
        loadMessages()
    }

    fun sendMessage(content: String) {
        val message = Message(0, content, System.currentTimeMillis(), true)
        CoroutineScope(Dispatchers.IO).launch {
            db.messageDao().insert(message)
            loadMessages()
        }
        // Auto reply
        CoroutineScope(Dispatchers.IO).launch {
            Thread.sleep(2000)
            db.messageDao().insert(Message(0, "You said: $content", System.currentTimeMillis(), false))
            loadMessages()
        }
    }

    private fun loadMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            val allMessages = db.messageDao().getAll()
            _messages.postValue(allMessages)
        }
    }
}
