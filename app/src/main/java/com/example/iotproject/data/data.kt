package com.example.iotproject.data

import com.google.gson.annotations.SerializedName


data class data(
    @SerializedName("Device Name")
    val deviceName: String,
    val Value: String
)