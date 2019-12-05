package com.kapuaStudio.ner.ui.buscar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FindViewModel :ViewModel(){
    private val _text = MutableLiveData<String>().apply{
        value = "Aqui es donde se busca"
    }
    val text: LiveData<String> = _text

}