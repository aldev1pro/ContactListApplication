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
fun Home(navigation: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier
                    //hard coded
               // .clickable { navigation.navigate(route = "detail/"+1) }
               // .clickable { navigation.navigate(MyRoute.Detail.detailScreenPassId(id = 10,name = "Joe")) }
                    //For optional argument
                .clickable { navigation.navigate(route = MyRoute.Detail.detailScreenPassId(name = "Joe Biden")) }
        )
    }

}