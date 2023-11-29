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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.home.customtablist.ui.theme.CustomTabListTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTabListTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    val tabTitle = listOf(
                        "Day", "Image"
                    )
                    val pagerState = rememberPagerState(pageCount = tabTitle.size)
                    val scope = rememberCoroutineScope()
                    LazyRow(
                        modifier = Modifier
                            .padding(start = 14.dp, top = 40.dp)
                            .fillMaxWidth()
                    ) {
                        items(tabTitle.size) {
                            val selectedTab = pagerState.currentPage == it
                            Box(
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (selectedTab) Color.Blue else Color.Gray)
                                    .padding(8.dp)
                                    .clickable {
                                        scope.launch {
                                            pagerState.animateScrollToPage(it)
                                        }
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
                        modifier = Modifier.padding(top = 12.dp),
                        state = pagerState
                    ) {page ->
                        when(page) {
                            0 -> { ItemListDay() }
                            1 -> {
                                Image(
                                    painter = painterResource(id = R.drawable.francesco_unsplash),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(start = 14.dp, top = 18.dp, end = 14.dp)
                                        .clip(RoundedCornerShape(30.dp))
                                        .fillMaxWidth()
                                        .height(280.dp)
                                )
                            }
                        }
                    }
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