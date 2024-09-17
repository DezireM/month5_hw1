package com.example.myapplication.ui.activity

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.CounterModel

class MainViewModel: ViewModel() {

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> = _count

    private val _toastMessage = MutableLiveData<String?>()
    val toastMessage: LiveData<String?> = _toastMessage

    private val _textColor = MutableLiveData<Int>()
    val textColor: LiveData<Int> = _textColor

    private val model = CounterModel()

    fun increment() {
        model.increment()
        updateLiveData()
    }

    fun decrement() {
        model.decrement()
        updateLiveData()
    }

    private fun updateLiveData() {
        _count.value = model.count
        checkForSpecialValue()
    }

    private fun checkForSpecialValue() {
        when(model.count) {
            10 -> {_toastMessage.value = "Поздравляю"
            _toastMessage.value = null
        }
            15 -> _textColor.value = Color.GREEN
            else -> _textColor.value = Color.BLACK
        }
    }
}