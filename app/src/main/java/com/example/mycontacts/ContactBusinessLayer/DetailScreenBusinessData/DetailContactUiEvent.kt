package com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData

import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.MainUiEvent
import com.example.mycontacts.ContactsData.Contact

sealed class DetailContactUiEvent{

    data class ShowSnackbar(
        val message:String,
        val action:String
    ): DetailContactUiEvent()
    data class NavigateToEditContactScreenWithData(val route:String): DetailContactUiEvent()
    object PopToMainScreenContact:DetailContactUiEvent()
}
