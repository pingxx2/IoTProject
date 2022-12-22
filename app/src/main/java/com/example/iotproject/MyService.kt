package com.example.iotproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()

        // notification channel 설정
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "화재 감지 알람"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel("CHANNEL_ID", name, importance)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_baseline_home_24)
            .setContentText("화재발생")
        startForeground(1, builder.build())
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

}