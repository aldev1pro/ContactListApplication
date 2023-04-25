package com.example.mycontacts.ContactUi

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.ContactNavigationRoutes
import com.example.mycontacts.ContactsData.Contact


@Composable
fun ScreenNavigatorHost(nav:NavHostController){
    NavHost(navController = nav, startDestination = ContactNavigationRoutes.MainScreen.route, builder = {
        composable(route = ContactNavigationRoutes.MainScreen.route){
            MainContactScreen(
                onNavigate = {
                    nav.navigate(it.route)

                }

            )
        }
        composable(route = ContactNavigationRoutes.DetailScreen.route + "?contactId={contactId}",
            arguments = listOf(navArgument(name = "contactId"){
                type = NavType.IntType
                defaultValue = -1
            })
        ){
            DetailContactScreen(
                PopUP = {
                    nav.navigate(it.route)
                   // nav.popBackStack()
                   // nav.navigate(ContactNavigationRoutes.MainScreen.route)
                }
            )
        }
        composable(ContactNavigationRoutes.EditContactScreen.route + "?contactId={contactId}",
            arguments = listOf(navArgument(name = "contactId"){
                type = NavType.IntType
                defaultValue = -1
            })
        ){
            EditContactScreen(PopBackStack = {
               // nav.popBackStack()
                nav.navigate(it.path)
            })
        }
    })


}

