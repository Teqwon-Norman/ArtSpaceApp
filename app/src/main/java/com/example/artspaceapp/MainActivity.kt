package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

data class GalleryItem(
    val image: Int,
    val title: Int,
    val artist: Int,
    val year: Int
)

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier
) {
    val galleryItems = listOf(
        GalleryItem(
            image = R.drawable.image_art_1,
            title = R.string.image_title_1,
            artist = R.string.image_artist_1,
            year = R.string.image_year_1,
        ),
        GalleryItem(
            image = R.drawable.image_art_2,
            title = R.string.image_title_2,
            artist = R.string.image_artist_2,
            year = R.string.image_year_2
        ),
        GalleryItem(
            image = R.drawable.image_art_3,
            title = R.string.image_title_3,
            artist = R.string.image_artist_3,
            year = R.string.image_year_3
        )
    )

    var gallerySlide by remember { mutableIntStateOf(0) }

    when (gallerySlide) {
        galleryItems.size -> gallerySlide = 0
        -1 -> gallerySlide = galleryItems.size - 1
    }

    val currentItem = galleryItems[gallerySlide]

    Box(
        modifier
            .statusBarsPadding()
            .safeDrawingPadding()
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(358.dp, 450.dp)
            .offset(y = (-100).dp)) {
            Image(
                painter = painterResource(id = currentItem.image),
                contentDescription = null,
                modifier = Modifier
                    .border(24.dp, Color.White, RectangleShape)
                    .shadow(6.dp, RectangleShape)
                    .clip(RectangleShape),
                contentScale = ContentScale.FillWidth
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtDescription(
                modifier = Modifier
                    .background(Color(0xFFdfe3ef))
                    .padding(16.dp),
                title = stringResource(id = currentItem.title),
                artist = stringResource(id = currentItem.artist),
                year = stringResource(id = currentItem.year)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavigationButton(
                    Modifier,
                    onClick = { gallerySlide -= 1 },
                    text = "Previous"
                )
                NavigationButton(
                    Modifier,
                    onClick = { gallerySlide += 1 },
                    text = "Next"
                )
            }
        }
    }
}

@Composable
fun ArtDescription(
    modifier: Modifier,
    title: String,
    artist: String,
    year: String
) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 24.sp
                )
            ) {
                append(title + "\n")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            ) {
                append(artist)
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            ) {
                append(year)
            }
        },
        modifier = modifier
    )
}

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier
            .height(35.dp)
            .width(150.dp),
        onClick = onClick
    ) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}