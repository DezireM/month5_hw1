package com.example.myapplication



interface LoveContract {

    interface View {
        fun showResult(result: LoveModel)
        fun showError(message: String)
    }
    interface Presenter {
        fun getPercentage(firstName: String, secondName: String)
    }
}