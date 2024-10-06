package com.aditya.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class Counter:ViewModel() {

    private var counterValue:Int=0
    private  var isRunning:Boolean=true

 val count=MutableStateFlow<Int>(0)




    fun start(): Flow<Int> = flow {
        while(isRunning){
            emit(counterValue)
            delay(1000)
            counterValue++
        }

    }


    fun stop(){
        isRunning=false
        counterValue=0
    }

}