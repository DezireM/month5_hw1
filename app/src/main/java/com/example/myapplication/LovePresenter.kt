package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.LoveApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LovePresenter @Inject constructor(
    private val api: LoveApiService,
): ViewModel() {

    val loveResultData = MutableLiveData<LoveModel>()
    val errorData = MutableLiveData<String>()

     fun getPercentage(firstName: String, secondName: String) {
        api.getPercentage(
            key = "c19b61b845mshb06557c9a284de4p1c4c7ajsn4839e0b0c896",
            host = "love-calculator.p.rapidapi.com",
            firstName = firstName,
            secondName = secondName).enqueue(object : Callback<LoveModel> {
            override fun onResponse(p0: Call<LoveModel>, p1: Response<LoveModel>) {
                if (p1.isSuccessful && p1.body() != null) {
                    loveResultData.postValue(p1.body()!!)
                } else {
                    errorData.postValue("Error : ${p1.message()}")
                }
            }

            override fun onFailure(p0: Call<LoveModel>, p1: Throwable) {
                errorData.postValue("Failure: ${p1.message}")
            }
        })
    }

}