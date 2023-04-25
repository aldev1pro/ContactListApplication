package com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer

import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.ContactNavigationRoutes
import com.example.mycontacts.ContactsData.Contact

sealed class EditContactListEvent{

    object OnSaveContact:EditContactListEvent()
    object PopToMainScreenFromEditScreen:EditContactListEvent()
    data class OnNameChange(var name: String):EditContactListEvent()
    data class OnSurnameChange(var surname:String):EditContactListEvent()
    data class OnNumberChange(var number:String):EditContactListEvent()
    data class OnPlaceChange(var place: String):EditContactListEvent()
    data class OnEmailChange(var email: String):EditContactListEvent()
}
