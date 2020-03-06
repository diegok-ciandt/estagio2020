package com.ciandt.estagio2020.oitavaaula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private var lastPersonSelected : Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.layoutLifeCycleOwner = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        setupList()
        updateList(null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_share) {
            share()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupList() {
        binding.recyclerListView.adapter = UserListAdapter(object : OnClickUser {
            override fun onClick(person: Person) {
                lastPersonSelected = person
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
        binding.addComponent.visibility = View.VISIBLE
    }

    fun deleteAll(v: View) {
        viewModel.deleteAll()
    }

    fun share() {
        lastPersonSelected?.let {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it.toString())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onBackPressed() {
        if (binding.addComponent.visibility == View.VISIBLE) {
            binding.addComponent.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

}
