package com.ciandt.estagio2020.oitavaaula

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val testValue = MutableLiveData<String>("Room Database")

    val loading = MutableLiveData<Boolean>(false)

    val loadingState: LiveData<Int> = Transformations.map(loading) {
        if (it) return@map View.VISIBLE else return@map View.GONE
    }

    fun requestRepositoryData() {
        loading.value = true
        Thread(Runnable {
            fakeRepository()
        }).start()
    }

    private fun fakeRepository() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        callBackRepository()
    }

    private fun callBackRepository() {
        loading.postValue(false)
    }


}