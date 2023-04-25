package com.example.mycontacts.Fitness

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FitnessDetailScreen(nav:NavHostController,extract:FitnessData){
    Column{
        Image(
            painter = painterResource(extract.image) ,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(7.dp))
        Text(text = extract.name)
    }
    Button(onClick = {
        nav.popBackStack()
    }) {
        Text(text = "Go Back")
    }

}