package com.learning.upc2302cc238eau202010057.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonEntity(
    @PrimaryKey val name: String,
    val title: String,
    val gender: String,
    val city: String,
    val picture: String
)
