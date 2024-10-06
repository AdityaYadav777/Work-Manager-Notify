package com.aditya.myapplication

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CounterService():Service() {



    private val counter=Counter()

    override fun onBind(intent: Intent?): IBinder? {
      return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action){
            CounterAction.START.name -> start()
            CounterAction.STOP. name -> stop()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(){
        CoroutineScope(Dispatchers.IO).launch {
           counter.start().collect{value->
               Log.i("Counter",value.toString())
               notification(value)
           }
        }
    }



    private fun notification(counterValue:Int){
        val REQUEST_CODE=202
         val myIntent= Intent(this,MainActivity::class.java)
         val pendingIntent= PendingIntent.getActivity(
            this,
            REQUEST_CODE,
            myIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId="aditya"
        val counterNotification=NotificationCompat
            .Builder(this,channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Counter")
            .setContentIntent(pendingIntent)
            .setContentText(counterValue.toString())
            .setStyle(NotificationCompat.BigTextStyle())
            .build()

            startForeground(1,counterNotification)
    }


    private fun stop(){
        counter.stop()
        stopSelf()
    }


enum class CounterAction{
    START,STOP,RESUME,PAUSE
}

}
