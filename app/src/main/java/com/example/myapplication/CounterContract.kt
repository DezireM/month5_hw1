package com.example.myapplication

interface CounterContract {

    fun showResult(count: Int)
    fun showToast(message: String)
    fun changeTextColor(color: Int)
}