package com.learning.upc2302cc238eau202010057.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    fun save(person: PersonEntity)

    @Delete
    fun delete(person: PersonEntity)

    @Query("select * from persons where name=:name")
    fun getByName(name: String): PersonEntity?

    @Query("select * from persons")
    fun getAll(): List<PersonEntity>
}