package com.learning.upc2302cc238eau202010057.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Home ( navigate: (String) -> Unit ) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage (
            imageModel = { "https://cdn-icons-png.flaticon.com/512/4951/4951182.png" },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit),
            modifier = Modifier.size(100.dp)
        )
        Button(onClick = { navigate("Persons") }) {
           Text (text = "Persons")
        }

        Button(onClick = { navigate("Favorites") }) {
            Text(text = "Favorites")
        }
    }
}