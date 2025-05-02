package com.example.phoneforfilm.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LanguageViewModel : ViewModel() {
    private val _language = MutableLiveData<String>()
    val language: LiveData<String> = _language

    fun getLanguage(): String? = _language.value
    fun setLanguage(lang: String) {
        _language.value = lang
    }
}
