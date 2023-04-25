package com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData

import com.example.mycontacts.ContactsData.Contact

sealed class MainContactListEvent{

    data class OnViewDetailContact(val contact:Contact): MainContactListEvent()
    data class OnSearchContact(val contact: Contact): MainContactListEvent()
    object OnAddContact: MainContactListEvent()

}
