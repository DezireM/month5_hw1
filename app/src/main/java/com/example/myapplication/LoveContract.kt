package com.example.myapplication

import com.example.myapplication.data.network.LoveResult


interface LoveContract {

    interface View {
        fun showResult(result: LoveResult)
        fun showError(message: String)
    }
    interface Presenter {
        fun getPercentage(firstName: String, secondName: String)
    }
}