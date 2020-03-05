package com.ciandt.estagio2020.oitavaaula

import com.ciandt.estagio2020.oitavaaula.database.Person

interface OnClickUser {
    fun onClick(person: Person)

    fun onLongClick(position: Int)
}