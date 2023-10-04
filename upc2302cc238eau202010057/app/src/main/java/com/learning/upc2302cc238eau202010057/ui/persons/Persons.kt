package com.learning.upc2302cc238eau202010057.ui.persons

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.learning.upc2302cc238eau202010057.R
import com.learning.upc2302cc238eau202010057.data.local.AppDatabase
import com.learning.upc2302cc238eau202010057.data.model.Person
import com.learning.upc2302cc238eau202010057.repository.PersonRepository
import com.learning.upc2302cc238eau202010057.utils.Result

@Composable
fun Persons () {

    val textQuery = remember { mutableStateOf("")}
    val persons = remember { mutableStateOf(listOf<Person>()) }

    Column {
        PersonSearchBar(textQuery, persons)
        PersonList(persons)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonSearchBar(
    textQuery: MutableState<String>,
    persons: MutableState<List<Person>>
) {
    val context = LocalContext.current
    val personDao = AppDatabase.getInstance(context).personDao()
    val repository = PersonRepository(personDao)

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, top = 5.dp, end = 5.dp),
        value = textQuery.value,
        onValueChange = { newTextQuery ->
            textQuery.value = newTextQuery
        },
        placeholder = { Text(stringResource(R.string.search_a_number_of_persons))},
        leadingIcon = { Icon(Icons.Filled.Search, stringResource(R.string.searchicon))},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                repository.searchByNumberOfPersons(textQuery.value.toInt()) { result ->
                    if (result is Result.Success) {
                        persons.value = result.data!!
                    } else {
                        Toast.makeText(context, result.message!!, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    )
    )
}