package com.ramadhan.movlist

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight
// ... import lainnya
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramadhan.movlist.model.MovieDataSource
import com.ramadhan.movlist.ui.theme.MovListTheme
import components.MovieItem   // ⬅️ IMPORT COMPONENT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class) // Wajib untuk TopAppBar
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val movies = MovieDataSource.dummyMovies

    // 1. Gunakan Scaffold sebagai root layout
    Scaffold(
        modifier = modifier,
        // 2. Tambahkan Header (Top Bar)
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "MovList",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    // Menggunakan warna Primary dari tema
                    containerColor = MaterialTheme.colorScheme.primary,
                    // Warna teks kontras dengan background
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        // 3. Masukkan Konten Grid ke dalam Scaffold
        // PENTING: Gunakan innerPadding pada modifier Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(innerPadding) // <-- JANGAN LUPA INI
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovListTheme {
        Greeting("Android")
    }
}
