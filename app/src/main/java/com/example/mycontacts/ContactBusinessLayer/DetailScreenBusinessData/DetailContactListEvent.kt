package com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData

import com.example.mycontacts.ContactsData.Contact

sealed class DetailContactListEvent{
    data class OnDeleteContact(val contact: Contact):DetailContactListEvent()
    object OnUndoContact:DetailContactListEvent()
    data class OnEditScreenNavigate(val contact: Contact):DetailContactListEvent()
    object OnBackPressToMainContact:DetailContactListEvent()

}
