package com.learning.upc2302cc238eau202010057.repository

import com.learning.upc2302cc238eau202010057.data.local.PersonDao
import com.learning.upc2302cc238eau202010057.data.local.PersonEntity
import com.learning.upc2302cc238eau202010057.data.model.Person
import com.learning.upc2302cc238eau202010057.data.model.PersonResponse
import com.learning.upc2302cc238eau202010057.data.remote.ApiClient
import com.learning.upc2302cc238eau202010057.data.remote.PersonService
import com.learning.upc2302cc238eau202010057.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository (
    private val personDao: PersonDao,
    private val personService: PersonService = ApiClient.getPersonService()
) {
  fun searchByNumberOfPersons(number: Int, callback: (Result<List<Person>>) -> Unit) {
      val searchByNumber = personService.searchByNumberOfPersons(number = number)
      searchByNumber.enqueue(object : Callback<PersonResponse> {
          override fun onResponse(call: Call<PersonResponse>, response: Response<PersonResponse>) {
              if (response.isSuccessful) {
                  try {
                      val persons = response.body()!!.persons
                      persons.forEach { person ->
                          person.isFavorite = personDao.getByName(person.name.first) != null
                      }
                      callback(Result.Success(response.body()!!.persons))
                  } catch (e: Exception) {
                      callback(Result.Success(listOf<Person>()))
                  }
              }
          }

          override fun onFailure(call: Call<PersonResponse>, t: Throwable) {
              callback(Result.Error(t.localizedMessage as String))
          }

      })
  }
    fun save(person: Person) {
        personDao.save(PersonEntity(
            person.name.first,
            person.name.title,
            person.gender,
            person.location.city,
            person.picture.url))
        person.isFavorite = true
    }

    fun delete(person: Person) {
        personDao.delete(PersonEntity(
            person.name.first,
            person.name.title,
            person.gender,
            person.location.city,
            person.picture.url))
        person.isFavorite = false
    }

    fun delete(person: PersonEntity) {
        personDao.delete(person)
    }

    fun getAllFavorites(): List<PersonEntity> {
        return personDao.getAll()
    }
}