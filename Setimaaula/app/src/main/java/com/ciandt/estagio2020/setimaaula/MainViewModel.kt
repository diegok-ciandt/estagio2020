package com.ciandt.estagio2020.setimaaula

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var loading : MutableLiveData<Boolean>  = MutableLiveData(false)
    var response : MutableLiveData<String> = MutableLiveData("")
    var strText : MutableLiveData<String> = MutableLiveData("Empty 2")

    fun sendMessage() {
        loading.value = true
        Thread(Runnable {
            callAPI()
        }).start()
    }

    private fun callbackAPI() {
        loading.postValue(false)
        response.postValue("Success API response")
        strText.postValue("Return Text")
    }

    private fun callAPI() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        callbackAPI()
    }
}