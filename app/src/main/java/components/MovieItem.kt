package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramadhan.movlist.R
import com.ramadhan.movlist.model.Movie
import com.ramadhan.movlist.ui.theme.MovListTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    // 1. Definisikan State (Status Like)
    var isLiked by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(200.dp)
            .padding(8.dp)
    ) {
        Column {
            // 2. Gunakan BOX untuk menumpuk Gambar dan Tombol Like
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                // Layer Bawah: Poster
                Image(
                    painter = painterResource(id = movie.imageRes),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Layer Atas: Tombol Like (Pojok Kanan Atas)
                IconButton(
                    onClick = { isLiked = !isLiked }, // Toggle nilai true/false
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        // Ganti ikon berdasarkan state
                        imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        // Ganti warna berdasarkan state
                        tint = if (isLiked) Color.Red else Color.White,
                        contentDescription = "Favorite Button"
                    )
                }
            }

            // Judul & Rating (Tidak berubah)
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = "★ ${movie.rating} / ${movie.year}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovListTheme {
        val dummy = Movie(
            id = 1,
            title = "Demo Movie",
            year = "2024",
            rating = 5.0,
            imageRes = R.drawable.poster_interstellar // pastikan ada
        )
        MovieItem(movie = dummy)
    }
}
