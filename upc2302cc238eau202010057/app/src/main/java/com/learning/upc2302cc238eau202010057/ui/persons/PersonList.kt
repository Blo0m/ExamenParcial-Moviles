package com.learning.upc2302cc238eau202010057.ui.persons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.learning.upc2302cc238eau202010057.data.local.AppDatabase
import com.learning.upc2302cc238eau202010057.data.model.Person
import com.learning.upc2302cc238eau202010057.repository.PersonRepository
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PersonList(persons: MutableState<List<Person>>) {
    val context = LocalContext.current
    val personDao = AppDatabase.getInstance(context).personDao()
    val personRepository = PersonRepository(personDao)

    LazyColumn {
        items(persons.value) { person ->
            PersonCard( person = person,
                deletePerson = {
                    personRepository.delete(person)
                    person.isFavorite = false
                },
                insertPerson = {
                    personRepository.save(person)
                    person.isFavorite = false
                })
        }
    }
}

@Composable
fun PersonCard(
    person: Person,
    deletePerson: () -> Unit,
    insertPerson: () -> Unit
) {
    val isFavorite = remember {
        mutableStateOf(false)
    }

    isFavorite.value = person.isFavorite
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PersonImage(person.picture.url)
            Column(modifier = Modifier.weight(5f).padding(horizontal = 5.dp)) {
                Text(text = person.name.first + " " + person.name.last, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = person.email)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = person.cell)
            }
            IconButton(modifier = Modifier.weight(1f), onClick = {
                if (isFavorite.value) {
                    deletePerson()
                } else {
                    insertPerson()
                }
                isFavorite.value = !isFavorite.value
            }) {
                Icon(
                    Icons.Default.Favorite,
                    null,
                    tint = if (isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun PersonImage(url: String) {
    GlideImage(
        imageModel = { url },
        imageOptions = ImageOptions(contentScale = ContentScale.Fit),
        modifier = Modifier.size(92.dp)
    )
}