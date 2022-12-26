package com.example.iotproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.app.Service.START_REDELIVER_INTENT
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.iotproject.data.SensorData
import com.example.iotproject.service.APISensor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.timer

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        //화재가 감지되면 알람을 울림
        if(getFlameData()=="true"){
            // notification channel 설정
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name = "화재 감지 알람"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val notificationChannel = NotificationChannel("CHANNEL_ID", name, importance)

                notificationManager.createNotificationChannel(notificationChannel)
            }
            val builder = NotificationCompat.Builder(this@MyService, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_baseline_home_24)
                .setContentText("화재발생")
            startForeground(1, builder.build())
        } else{ // 화재가 감지되지 않으면 알람 취소
            notificationManager.cancelAll()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 서비스 처음 시작
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        // 서비스 종료
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun getFlameData(): String{
        var flame_value: String = ""
        // 화재 감지 값을 가져옴
        APISensor.getService()
            .getSensorValue("flame_detect")
            .enqueue(object : Callback<SensorData> {
                override fun onResponse(call: Call<SensorData>, response: Response<SensorData>) {
                    if(response.isSuccessful){
                        val data = response.body()
                        flame_value = data?.value!!
                    }
                }
                override fun onFailure(call: Call<SensorData>, t: Throwable) {
                    Log.i("isWorking?", "Fail...")
                }
            })
        return flame_value
    }
}