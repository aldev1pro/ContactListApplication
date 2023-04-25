package com.example.mycontacts.Fitness

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun FitnessStarterScreen(nav:NavHostController){
    Column{

        //val nol = remember{FitnessInitializer.myFitnessList}
        val nol = FitnessInitializer.myFitnessList
        LazyColumn{
            items(items = nol){
                FitnessHolder(
                    extract = it,
                    navigator = nav )
            }
        }
    }

}