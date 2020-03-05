package com.ciandt.estagio2020.oitavaaula

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.estagio2020.oitavaaula.databinding.ListItemCellBinding

class UserListAdapter(var onClickListener: OnClickUser) : RecyclerView.Adapter<UserViewHolder>() {


    private var list = ArrayList<Person>()

    fun updateList(listLocal :ArrayList<Person>) {
        list = listLocal
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding : ListItemCellBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.list_item_cell,
            parent,
            false)


        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.person = list.get(position)
        holder.binding.onClickListener = onClickListener
    }

}