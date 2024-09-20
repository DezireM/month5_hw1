package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.network.LoveApiService
import com.example.myapplication.network.LoveModel
import com.example.myapplication.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ViewModel (private val contract: LoveContract.View) : LoveContract.Presenter {
    override fun getPercentage(firstName: String, secondName: String) {
        RetrofitService.api.getPercentage(
            key = "c19b61b845mshb06557c9a284de4p1c4c7ajsn4839e0b0c896",
            host = "love-calculator.p.rapidapi.com",
            firstName = firstName,
            secondName = secondName).enqueue(object : Callback<LoveModel> {
            override fun onResponse(p0: Call<LoveModel>, p1: Response<LoveModel>) {
                if (p1.isSuccessful && p1.body() != null) {
                    contract.showResult(p1.body()!!)
                } else {
                    contract.showError("Error : ${p1.message()}")
                }
            }

            override fun onFailure(p0: Call<LoveModel>, p1: Throwable) {
                contract.showError("Failure: ${p1.message}")
            }
        })
    }

}