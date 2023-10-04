package com.learning.upc2302cc238eau202010057.data.remote

import com.learning.upc2302cc238eau202010057.data.model.PersonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonService {

    @GET(".")
    fun searchByNumberOfPersons(@Query("results") number: Int): Call<PersonResponse>

}