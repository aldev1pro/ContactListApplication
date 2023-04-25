package com.example.practice

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavHost(navig: NavHostController){
    androidx.navigation.compose.NavHost(navController = navig, startDestination = MyRoute.Home.route, builder = {

        composable(route = MyRoute.Home.route){
            Home(navigation = navig)
        }

        composable(route = MyRoute.Detail.route,
            arguments = listOf(
//                navArgument(DETAIL_ARGUMENT_KEY) {
//                type = NavType.IntType
//            },
//                navArgument(DETAIL_ARGUMENT_KEY2){
//                    type = NavType.StringType
//                }
//            )
            navArgument(DETAIL_ARGUMENT_KEY){
                type = NavType.IntType
                defaultValue =  0

            },
                navArgument(DETAIL_ARGUMENT_KEY2){
                    type = NavType.StringType
                }
        )

        )
            {
            Log.d("Args",it.arguments?.getInt("id").toString())
            Log.d("Args", it.arguments?.get(DETAIL_ARGUMENT_KEY2).toString())
//            Log.d("Args", it.arguments?.get(DETAIL_ARGUMENT_KEY2).toString())
//            Log.d("Args", it.arguments?.get("${DETAIL_ARGUMENT_KEY},${DETAIL_ARGUMENT_KEY2}").toString())
            Detail(navigation = navig)
        }
    })}


