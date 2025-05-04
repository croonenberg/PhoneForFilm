// Using Hilt, no manual factory needed. Remove this file or leave empty.
package com.example.phoneforfilm.viewmodel

import androidx.lifecycle.ViewModelProvider

class ChatViewModelFactory : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        throw UnsupportedOperationException("Use Hilt injection for ChatViewModel")
    }
}
