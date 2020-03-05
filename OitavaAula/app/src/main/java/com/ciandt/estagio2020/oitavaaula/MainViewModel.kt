package com.ciandt.estagio2020.oitavaaula

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val testValue = MutableLiveData<String>("Room Database")

}