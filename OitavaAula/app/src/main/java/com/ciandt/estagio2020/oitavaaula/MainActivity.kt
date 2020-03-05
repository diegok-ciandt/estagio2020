package com.ciandt.estagio2020.oitavaaula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ciandt.estagio2020.oitavaaula.databinding.ActivityMainBinding
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var myList = ArrayList<String>()
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        setupList()
        updateList(null)
    }

    private fun setupList() {
        binding.recyclerListView.adapter = UserListAdapter(object : OnClickUser {
            override fun onClick(person: Person) {
                Log.d("TAG", "Click: "+person.firstName)
                viewModel.testValue.postValue(person.firstName)
            }
        })
    }

    var qtd = 2
    fun updateList(v: View?) {
        viewModel.requestRepositoryData()
        val list = ArrayList<Person>()
        for (i in 0..qtd) {
            val age = Random.nextInt(90) + 1
            val tempUser = Person("Person"+i, "Surname", age)
            list.add(tempUser)
        }

        val myListAdapter : UserListAdapter = binding.recyclerListView.adapter as UserListAdapter
        myListAdapter.updateList(list)
        qtd += 2
    }

}
