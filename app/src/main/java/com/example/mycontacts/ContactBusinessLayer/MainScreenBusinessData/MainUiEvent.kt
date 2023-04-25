package com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData

sealed class MainUiEvent{

    data class NavigateToAnyScreen(val route:String) : MainUiEvent()

}

