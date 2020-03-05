package com.ciandt.estagio2020.oitavaaula

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ciandt.estagio2020.oitavaaula.database.AppDatabase
import com.ciandt.estagio2020.oitavaaula.database.Person
import com.ciandt.estagio2020.oitavaaula.database.PersonDao
import kotlin.random.Random

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val testValue = MutableLiveData<String>("Room Database")

    val loading = MutableLiveData<Boolean>(false)

    private var db : AppDatabase

    val listPerson = MutableLiveData<ArrayList<Person>>(ArrayList<Person>())

    init {
        db = AppDatabase.getInstance(application)
    }

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
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        callBackRepository()
    }

    private fun callBackRepository() {
        loading.postValue(false)
        val age = Random.nextInt(90) + 1
        val tempUser = Person("Person", "Surname", age)
        listPerson.value?.add(tempUser)
        listPerson.postValue(listPerson.value)
    }

    fun insertPerson() {
        Thread(Runnable {
            val person = Person("Domenic","Royals", 25)
            db.personDao().insert(person)
            getAllPerson()
        }).start()
    }

    fun getAllPerson() {
        Thread(Runnable {
            var list = db.personDao().getAll()
            listPerson.postValue(ArrayList(list))
        }).start()
    }

    fun deleteAll() {
        Thread(Runnable {
            db.personDao().deleteAll()
            getAllPerson()
        }).start()
    }

    fun delete(person: Person?) {
        person?.let {
            Thread(Runnable {
                db.personDao().delete(it)
                getAllPerson()
            }).start()
        }

//        use let instead of != null
//        if (person != null) {
//            Thread(Runnable {
//                db.personDao().delete(person)
//                getAllPerson()
//            }).start()
//        }
    }

}