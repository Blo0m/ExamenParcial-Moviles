package com.learning.upc2302cc238eau202010057.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_BASE_URL = "https://randomuser.me/api/"
    private var personService: PersonService? = null

    fun getPersonService(): PersonService {
        if(personService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            personService = retrofit.create(PersonService::class.java)
        }

        return personService as PersonService
    }
}