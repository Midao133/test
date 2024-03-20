package com.example.exam2part2

import Data.ReadSongs
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exam2part2.ui.theme.Exam2part2Theme
import modele.Song
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.IconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exam2part2Theme {


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                    Row (
                        Modifier
                            .background(color = MaterialTheme.colorScheme.primary)
                            .fillMaxWidth()) {
                        Icon(
                            imageVector = Icons.Filled.Album,
                            tint = MaterialTheme.colorScheme.background,
                            contentDescription = "face",
                            modifier = Modifier
                                .size(72.dp)
                                .padding(4.dp)
                        )
                        Text("Catalogue de CD", style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.background)
                    }
                    MaListe(liste = ReadSongs().loadJson(applicationContext))
                }
                }
            }
        }
    }}


@Composable
fun MaListe(liste: List<Song>, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(liste){
                item -> UneSong(item, modifier)
        }
    }
}
@Composable
fun UnBouton(
    ouvert: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (ouvert) Icons.Filled.ExpandLess else Icons
                .Filled.ExpandMore, contentDescription = "Ouvrir",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}
@Composable
fun UneSong(song: Song, modifier: Modifier = Modifier){
    var ouvert = rememberSaveable { mutableStateOf(false) }
    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()
        .animateContentSize(
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier=Modifier.animateContentSize(animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        ))) {
            Row {
                Column {
                    Text(
                        text = "Titre: " + song.TITLE,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.displaySmall,
                    )
                    Text(
                        text = "Artist: "+song.ARTIST,
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Spacer(Modifier.weight(1f))
                UnBouton(ouvert.value, {ouvert.value = !ouvert.value})
            }


            if(ouvert.value){
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Pays: "+song.COUNTRY,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Compagnie: "+song.COMPANY,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Prix: %.2f".format(song.PRICE),
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Année: "+song.YEAR,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Exam2part2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            println("heyy?")
            Text("hii")
        }
    }
}