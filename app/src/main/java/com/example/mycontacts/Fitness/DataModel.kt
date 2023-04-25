package com.example.mycontacts.Fitness

import androidx.lifecycle.ViewModel
import com.example.mycontacts.R

class DataModel:ViewModel() {
    val myFitnessList = listOf(
        FitnessData(R.drawable.yoga1,"ChestPushUp"),
        FitnessData(R.drawable.yoga2,name = "Cest"),
        FitnessData(R.drawable.yoga3,name = "Cest"),
        FitnessData(R.drawable.yoga4,name = "Cest"),
        FitnessData(R.drawable.yoga5,name = "Cest"),
        FitnessData(R.drawable.yoga6,name = "Cest"),
        FitnessData(R.drawable.yoga7,name = "Cest"),
        FitnessData(R.drawable.yoga8,name = "Cest"),
    )
}