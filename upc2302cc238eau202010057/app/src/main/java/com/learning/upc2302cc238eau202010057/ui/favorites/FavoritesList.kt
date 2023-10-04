package com.learning.upc2302cc238eau202010057.ui.favorites

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.learning.upc2302cc238eau202010057.R
import com.learning.upc2302cc238eau202010057.data.local.AppDatabase
import com.learning.upc2302cc238eau202010057.data.local.PersonEntity
import com.learning.upc2302cc238eau202010057.repository.PersonRepository
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Favorites() {
    val context = LocalContext.current
    val personDao = AppDatabase.getInstance(context).personDao()
    val personRepository = PersonRepository(personDao)
    val persons = personRepository.getAllFavorites()
    if (persons.isEmpty())
        Text(
            text = stringResource(R.string.add_persons_to_favorites_to_see_results),
            modifier = Modifier.padding(15.dp)
        )

        LazyColumn {
            items(persons){ person ->
                FavoriteCard( person = person,
                    deletePerson = {
                        personRepository.delete(person)
                    }
                )
            }
        }
      }

@Composable
fun FavoriteCard(
    person: PersonEntity,
    deletePerson: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            PersonImage(person.picture)
            Column(modifier = Modifier
                .weight(5f)
                .padding(horizontal = 5.dp)) {
                Text(text = person.title + " " + person.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = person.gender)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = person.city)
            }
            IconButton(modifier = Modifier.weight(1f), onClick = {
                    deletePerson()
            }) {
                Icon(
                    Icons.Default.Delete,
                    null,
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