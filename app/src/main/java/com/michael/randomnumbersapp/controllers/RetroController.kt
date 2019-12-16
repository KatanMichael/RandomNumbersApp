package com.michael.randomnumbersapp.controllers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.michael.randomnumbersapp.interfaces.RequestListener
import com.michael.randomnumbersapp.interfaces.randomNumberService
import com.michael.randomnumbersapp.models.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroController
{
    val retroClient = Retrofit.Builder()
        .baseUrl("https://www.passwordrandom.com/"
        ).addConverterFactory(GsonConverterFactory.create())
        .build()

    val retroService = retroClient.create(randomNumberService::class.java)


    fun getRandomNumbers(requestListener: RequestListener<Response>)
    {
        retroService.getRandomNumbers().enqueue(object : Callback<Response>
        {
            override fun onFailure(call: Call<Response>, t: Throwable)
            {
                requestListener.onError(t.localizedMessage)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>)
            {
                val body = response.body()
                if(body != null)
                {
                    requestListener.onComplete(body)
                }
            }

        })
    }

    fun initGetNumbers() : MutableLiveData<Response>
    {
        var result = MutableLiveData<Response>()

        retroService.getRandomNumbers()
            .enqueue(object : Callback<Response>
            {
                override fun onFailure(call: Call<Response>, t: Throwable)
                {
                    result.postValue(null)
                }

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    val body = response.body()

                    if(body != null)
                    {
                        result.postValue(body)
                    }

                }

            })


        return result
    }
}
