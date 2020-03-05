package com.ciandt.estagio2020.oitavaaula.database

import androidx.room.*

@Dao
interface PersonDao {

    @Insert
    fun insert(vararg person: Person)

    @Delete
    fun delete(person: Person)

    @Query("DELETE FROM person")
    fun deleteAll()

    @Update
    fun update(person: Person)

    @Query("SELECT * FROM person ORDER BY firstName")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person WHERE firstName like :strValue")
    fun search(strValue: String): List<Person>

}