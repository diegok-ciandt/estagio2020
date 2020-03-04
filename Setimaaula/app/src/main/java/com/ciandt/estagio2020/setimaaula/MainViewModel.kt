package com.ciandt.estagio2020.setimaaula

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var loading : MutableLiveData<Boolean>  = MutableLiveData(false)
    var response : MutableLiveData<String> = MutableLiveData("")
    var strText : MutableLiveData<String> = MutableLiveData("Empty 2")
    var url : MutableLiveData<String> = MutableLiveData("https://www.internetvibes.net/wp-content/gallery/avatars/259.png")

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

    fun onClick() {
        sendMessage()
        needContext(getApplication())
    }


    private fun needContext(contexrt : Context) {

    }
}