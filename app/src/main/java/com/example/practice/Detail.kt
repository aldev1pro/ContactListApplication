package com.example.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Detail(navigation: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Detail Screen",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            modifier = Modifier
                .clickable { navigation.navigate(route = MyRoute.Home.route) }
        )
    }

}
