package com.aditya.myapplication

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel=ViewModelProvider(this)[Counter::class.java]

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
                requestNotificationPermission()
            }

            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {


                        Button(onClick = {
                            Intent(this@MainActivity,CounterService::class.java).also {
                              it.action=CounterService.CounterAction.START.name
                                startService(it)
                            }
                        }) {

                            Text(text = "Start Counter")

                        }
                        
                        Button(onClick = {
                            Intent(this@MainActivity,CounterService::class.java).also {
                                it.action=CounterService.CounterAction.STOP.name
                                startService(it)
                            }

                        }) {
                            Text(text = "Stop Service")
                        }
                    }
                }
            }
        }
    }


 @RequiresApi(Build.VERSION_CODES.TIRAMISU)
 private fun requestNotificationPermission(){
     ActivityCompat.requestPermissions(
         this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
         1000
     )

 }

}




