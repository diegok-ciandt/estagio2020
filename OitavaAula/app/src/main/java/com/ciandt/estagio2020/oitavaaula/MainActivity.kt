package com.ciandt.estagio2020.oitavaaula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ciandt.estagio2020.oitavaaula.databinding.ActivityMainBinding
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ciandt.estagio2020.oitavaaula.database.Person


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
                viewModel.testValue.postValue(person.firstName)
            }

            override fun onLongClick(position: Int) {
                val person = viewModel.listPerson.value?.get(position)
                Toast.makeText(this@MainActivity, ""+person?.firstName+" deleted!", Toast.LENGTH_SHORT).show()
                viewModel.delete(person)
            }
        })

        viewModel.listPerson.observe(this,  Observer {listPerson ->
            val myListAdapter : UserListAdapter = binding.recyclerListView.adapter as UserListAdapter
            myListAdapter.updateList(listPerson)
        })
    }

    fun updateList(v : View?) {
        viewModel.getAllPerson()
    }

    fun addItem(v: View) {
        viewModel.insertPerson()
    }

    fun deleteAll(v: View) {
        viewModel.deleteAll()
    }

}
