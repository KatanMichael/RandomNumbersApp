package com.michael.randomnumbersapp.interfaces

import com.michael.randomnumbersapp.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface randomNumberService
{
    @GET("/query")
    fun getRandomNumbers(@Query("command") command : String = "int",
                         @Query("min") minValue : Int = -50,
                         @Query("max") max: Int = 50,
                         @Query("format") format: String = "json",
                         @Query("count") count: Int = 20) : Call<Response>


}