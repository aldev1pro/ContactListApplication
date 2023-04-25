//package com.example.mycontacts.ui
//
//import android.util.Log
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.*
//import androidx.navigation.compose.composable
//import com.example.mycontacts.*
//
//fun NavGraphBuilder.partA(nav: NavController,mod:Riley){
//    navigation(startDestination = NestedScreen.one.route,route = NestedScreen.sectionA.route){
//
//        composable(route = NestedScreen.one.route,
//            arguments = listOf(navArgument("data"){
//                type = NavType.StringType
//            })
//        ){
//            val a = it.arguments?.getString("data")
//            val c = nav.previousBackStackEntry?.savedStateHandle?.get<Person>("son")
//            Log.d("args","Data captured ${c?.firstName}")
//            Log.d("args","Data captured ${c?.lastName}")
//
//            Book(nav,mod)
//        }
//        composable(route = NestedScreen.two.route){
//            Pencil(nav)
//        }
//    }
//
//}