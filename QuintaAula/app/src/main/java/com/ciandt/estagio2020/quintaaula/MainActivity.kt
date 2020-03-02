package com.ciandt.estagio2020.quintaaula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ciandt.estagio2020.quintaaula.databinding.ActivityMainBinding
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var myList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupList()
        updateList(null)
    }

    private fun setupList() {
        binding.recyclerListView.adapter = UserListAdapter(object : OnClickUser {
            override fun onClick(user: User) {
                //Toast.makeText(MainActivity@this, "Clicked!!!!", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "Click: "+user.name)


            }
        })
    }


    var qtd = 2
    fun updateList(v: View?) {
        val list = ArrayList<User>()
        for (i in 0..qtd) {
            val color = ColorDrawable(0xcc7700)
            val tempUser = User("User"+i, i*10f, i*3, i*1.1f, color)
            list.add(tempUser)
        }

        val myListAdapter : UserListAdapter = binding.recyclerListView.adapter as UserListAdapter
        myListAdapter.updateList(list)
        qtd += 2
    }

}
