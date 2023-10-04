package com.learning.upc2302cc238eau202010057.data.model

import com.google.gson.annotations.SerializedName

data class PersonResponse (
    @SerializedName("results")
    val persons: List<Person>
)

data class Person (
    val name: Name,
    val email: String,
    val cell: String,
    val picture: Picture,
    var isFavorite: Boolean,
    val gender: String,
    val location: Location
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Picture(
    @SerializedName("thumbnail")
    val url: String
)

data class Location(
    val city: String
)



