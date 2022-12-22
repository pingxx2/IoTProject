package com.example.iotproject.service

import com.example.iotproject.data.data
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "http://127.0.0.1:8000/"


interface SensorDataService{
    @GET("data/sensor/")
    fun getData(@Query("Device Name") deviceName: String): Call<data>
}


object APISensor{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): SensorDataService = retrofit.create(SensorDataService::class.java)
}