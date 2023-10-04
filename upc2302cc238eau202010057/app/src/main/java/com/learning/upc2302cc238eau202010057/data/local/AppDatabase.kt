package com.learning.upc2302cc238eau202010057.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java, "friend_finder_db"
            ).allowMainThreadQueries().build()
        }
    }
}