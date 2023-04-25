package com.example.mycontacts.Fitness

sealed class FitnessRoutes(val routes:String){
    object DetailScreen:FitnessRoutes(routes ="FitnessStarterScreen")
    object StarterScreen:FitnessRoutes(routes = "FitnessDetailScreen")
}
