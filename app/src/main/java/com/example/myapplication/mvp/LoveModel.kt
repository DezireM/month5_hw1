package com.example.myapplication.mvp

import com.example.myapplication.App
import com.example.myapplication.network.LoveResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveModel : LoveContract.Model {

    private val api = App.api

    override fun getLovePercentage(
        firstName: String,
        secondName: String,
        callback: (result: LoveResult?, error: String?) -> Unit
    ) {
        api?.getPercentage(
            key = "c19b61b845mshb06557c9a284de4p1c4c7ajsn4839e0b0c896",
            host = "love-calculator.p.rapidapi.com",
            firstName = firstName,
            secondName = secondName
        )?.enqueue(object : Callback<LoveResult> {
            override fun onResponse(call: Call<LoveResult>, response: Response<LoveResult>) {
                if (response.isSuccessful && response.body() != null) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoveResult>, throwable: Throwable) {
                callback(null, throwable.message)
            }
        })
    }

}