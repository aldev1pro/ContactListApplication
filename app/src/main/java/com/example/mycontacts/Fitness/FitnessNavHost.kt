package com.example.mycontacts.Fitness

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun FitnessNavHost(nav: NavHostController){
    NavHost(navController = nav, startDestination = FitnessRoutes.StarterScreen.routes , builder = {
        composable(FitnessRoutes.StarterScreen.routes){
            FitnessStarterScreen(nav)

        }
        composable(FitnessRoutes.DetailScreen.routes){
            val result = nav.previousBackStackEntry?.savedStateHandle?.get<FitnessData>("myKey")
            //result?.let {
                if(result !=null){
                    FitnessDetailScreen(nav,result)
                }
            // }
        }
    })

}

