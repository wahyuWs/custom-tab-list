package com.home.customtablist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.home.customtablist.ui.theme.CustomTabListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTabListTheme {

            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true, device = "id:Nexus 5")
@Composable
fun GreetingPreview() {
    CustomTabListTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            val tabTitle = listOf(
                "Day", "Image", "Task"
            )
            val pagerState = rememberPagerState(pageCount = tabTitle.size)
            var selectedTab by remember { mutableStateOf(0) }
            LazyRow(
                modifier = Modifier.padding(bottom = 12.dp),
                contentPadding = PaddingValues(top = 30.dp, start = 14.dp)
            ) {
                items(tabTitle.size) {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (selectedTab == it) Color.Blue else Color.Gray)
                            .padding(8.dp)
                            .clickable {
                                selectedTab = it
                            }
                    ) {
                        Text(
                            text = tabTitle[it],
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
            HorizontalPager(
                state = pagerState
            ) {page ->
                when(selectedTab) {
                    0 -> { ItemListDay() }
                    1 -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.francesco_unsplash),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(30.dp))
                                    .width(320.dp)
                                    .height(280.dp)
                            )
                        }
                    }
                    2 -> {}
                }
            }
        }
    }
}

@Composable
fun ItemListDay() {
    val day = listOf("Senin" ,"Selasa", "Rabu", "Kamis", "Jumat")
    LazyColumn {
        items(day.size) {
            Card(
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp, bottom = 12.dp)
                    .fillMaxWidth()
                    .height(80.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ini hari: ${day[it]}",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}