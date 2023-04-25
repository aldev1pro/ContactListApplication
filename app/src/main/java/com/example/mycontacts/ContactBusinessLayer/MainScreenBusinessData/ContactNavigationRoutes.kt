package com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData


sealed class ContactNavigationRoutes(val route:String){

    object MainScreen: ContactNavigationRoutes(route = "mainScreen")
    object DetailScreen: ContactNavigationRoutes(route = "detailScreen")
    object EditContactScreen: ContactNavigationRoutes(route = "editScreen")

}
