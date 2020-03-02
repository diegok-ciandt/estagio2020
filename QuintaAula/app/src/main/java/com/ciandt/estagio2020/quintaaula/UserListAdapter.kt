package com.ciandt.estagio2020.quintaaula

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.estagio2020.quintaaula.databinding.ListItemCellBinding

class UserListAdapter(var onClickListener: OnClickUser) : RecyclerView.Adapter<UserViewHolder>() {


    private var list = ArrayList<User>()

    fun updateList(listLocal :ArrayList<User>) {
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
        holder.binding.user = list.get(position)
        holder.binding.onClickListener = onClickListener
    }

}