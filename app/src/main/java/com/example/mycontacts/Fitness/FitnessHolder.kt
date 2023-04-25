package com.example.mycontacts.Fitness

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mycontacts.R

@Composable
fun FitnessHolder(extract:FitnessData,navigator:NavHostController){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .clickable {
                    navigator.currentBackStackEntry?.savedStateHandle?.set("myKey",extract)
                    navigator.navigate(FitnessRoutes.DetailScreen.routes)
                }
        ){
            Image(
                painter = painterResource(id = extract.image),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(7.dp))
            Text(
                text = extract.name,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

}