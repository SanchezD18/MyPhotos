package com.example.myphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myphotos.ui.theme.MyPhotosTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val urls = listOf(
//                "https://i.pinimg.com/736x/04/5d/13/045d13bee29ac49f2103035e9777dbf7.jpg",
//                "https://i.pinimg.com/1200x/28/73/c7/2873c7cb129a2ba7d846b0b95b3e6fa5.jpg",
//                "https://i.pinimg.com/1200x/44/e0/f5/44e0f54ff6653f479795d1c9876cc63d.jpg",
//                "https://i.pinimg.com/736x/02/bb/58/02bb582a80846db370f23d277d6d5dc4.jpg",
//                "https://i.pinimg.com/736x/e5/8c/6f/e58c6fd92e0629881907d833c45485da.jpg",
//                "https://i.pinimg.com/736x/b3/07/a1/b307a104214c0061b7d17584e974ac52.jpg",
//                "https://i.pinimg.com/736x/6d/40/b9/6d40b92b8002a0578002ff81dfa7042b.jpg"
//            )
//            val context = LocalContext.current
//            var selectedUrl by remember { mutableStateOf<String?>(null) }
            val items = listOf(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
            )

            val state = rememberCarouselState { items.size }
            val animationScope = rememberCoroutineScope()
            var selectedImage by remember { mutableStateOf<Int?>(null) }

            MyPhotosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HorizontalCenteredHeroCarousel(
                            state = state,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(221.dp)
                                .padding(horizontal = 24.dp),
                            itemSpacing = 8.dp,
                            contentPadding = PaddingValues(horizontal = 16.dp),
                        ) { index ->
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(205.dp)
                                    .maskClip(MaterialTheme.shapes.extraLarge)
                                    .clickable {
                                        selectedImage = items[index]
                                        animationScope.launch {
                                            state.animateScrollToItem(index)
                                        }
                                    },
                                painter = painterResource(items[index]),
                                contentDescription = "Imagen $index",
                                contentScale = ContentScale.Crop,
                            )
                        }

                        Spacer(modifier = Modifier.height(100.dp))

                        selectedImage?.let { imageRes ->
                            Image(
                                painter = painterResource(imageRes),
                                contentDescription = "Imagen ampliada",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }
        }
    }
}
    //Versión del ejercicio con AsyncImage, si se descomenta esto y se descomenta lo anterior y sus variables al principio, funciona perfectamente.
//                    LazyRow(
//                        contentPadding = PaddingValues(horizontal = 16.dp),
//                        horizontalArrangement = Arrangement.spacedBy(10.dp)
//
//                    ) {
//                        items(urls) { url ->
//                            AsyncImage(
//                                model = ImageRequest.Builder(context)
//                                    .data(url)
//                                    .crossfade(true)
//                                    .build(),
//                                contentDescription = "Imagen",
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .padding(innerPadding)
//                                    .size(200.dp)
//                                    .clip(RoundedCornerShape(16.dp))
//                                    .clickable{ selectedUrl = url })
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    selectedUrl?.let { url ->
//                        AsyncImage(
//                            model = ImageRequest.Builder(context)
//                                .data(url)
//                                .crossfade(true)
//                                .build(),
//                            contentDescription = "Imagen seleccionada",
//                            contentScale = ContentScale.Fit,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .fillMaxHeight()
//                                .padding(30.dp))
//                        }
//                    }




