package com.example.practice

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.parcelize.Parcelize

@Composable
fun NewNav(nav:NavHostController){

    val mySharedViewModel: SharedViewModel = viewModel()

    androidx.navigation.compose.NavHost(navController = nav, startDestination = NewRoute.First.route, builder = {
        composable(NewRoute.First.route){
            FirstScreen(nav, navParameter = mySharedViewModel)
        }
        composable(NewRoute.Second.route,){
//            LaunchedEffect(key1 = true ) {
//                val result =
//                    nav.previousBackStackEntry?.savedStateHandle?.get<Dummy>("personHolder")
//                Log.d("DetailScreen", "${result?.firstName}")
//                Log.d("DetailScreen", "${result?.secondName}")
//            }
                SecondScreen(nav, navParameter = mySharedViewModel)

        }
    })

}

@Composable
fun FirstScreen(nav: NavHostController, navParameter: SharedViewModel) {
    Surface(
        color = Color.Green
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,

            ) {
            Text(
                text = "NavigateToSecond",
                modifier = Modifier
                    .clickable {
                        val person1 = Dummy(firstName = "Michael", secondName = "Jordan")
//                        nav.currentBackStackEntry?.savedStateHandle?.set(
//                            key = "personHolder",
//                            value = person
//                        )
                        //Using shared viewModel
                        navParameter.addPerson(dataperson = person1)
                        nav.navigate(NewRoute.Second.route)
                }
            )
        }
    }
}

@Composable
fun SecondScreen(nav: NavHostController, navParameter: SharedViewModel) {
    Surface(
        color = Color.Red
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,

            ) {
            val receivePerson = navParameter.person
            LaunchedEffect(key1 = receivePerson){
            if (receivePerson != null) {
                Log.d("DetailScreen", receivePerson.firstName)
                Log.d("DetailScreen", receivePerson.secondName)
            }
            }
            Text(
                text = "NavigateToFirst",
                modifier = Modifier.clickable {
                    nav.navigate(NewRoute.First.route)
                }
            )
        }
    }
}

sealed class NewRoute(val route:String){
    object First: NewRoute("first")
    object Second: NewRoute("second")
}
@Parcelize
data class Dummy(
    val firstName:String,
    val secondName:String
    ) : Parcelable

class SharedViewModel:ViewModel(){
    var person by mutableStateOf<Dummy?>(null)
    private set

    fun addPerson(dataperson: Dummy){
        person = dataperson
    }


}



