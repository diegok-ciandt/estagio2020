package com.ciandt.estagio2020.oitavaaula.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(applicationContext : Application) : AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "data-base"
                    ).build()
                }

                INSTANCE = instance
                return instance
            }
        }
    }
}

