package com.example.iotproject.service

import com.example.iotproject.data.SensorData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path

private val BASE_URL = "http://192.168.35.114:8000/"


interface SensorDataService{
    @GET("/data/sensor/{dev}/latest_value")
    fun getSensorValue(@Path("dev") dev: String): Call<SensorData>
    @GET("/data/sensor/flame_detect")
    fun getFlameValue(): Call<SensorData>
}


object APISensor{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): SensorDataService = retrofit.create(SensorDataService::class.java)
}