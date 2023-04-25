package com.example.mycontacts.ContactBusinessLayer.EditScreenBusinessLayer

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.ContactNavigationRoutes
import com.example.mycontacts.ContactsData.Contact
import com.example.mycontacts.ContactsData.ContactDatabaseRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ContactEditViewModel(appli: Application, savedState: SavedStateHandle):ViewModel() {


    private val getContactDatabaseRepository = ContactDatabaseRepository(appli)

    private var _editContactUiEvent = Channel<EditContactUiEvent>()
    val editContactUiEvent = _editContactUiEvent.receiveAsFlow()

    var contact by mutableStateOf<Contact?>(null)

    init {
        val contactId = savedState.get<Int>("contactId")!!
        if(contactId != -1){
            viewModelScope.launch {
                getContactDatabaseRepository.getContactId(contactId)?.let { contact->
                    name = contact.firstName?:""
                    surname = contact.lastName?:""
                    number = contact.number
                    place = contact.place?:""
                    email = contact.email?:""
                    this@ContactEditViewModel.contact = contact
                }

            }
        }
    }

    var name by mutableStateOf("")
        private set

    var surname by mutableStateOf("")
        private set

    var number by mutableStateOf("")
        private set

    var place by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set


    fun editEvents(event:EditContactListEvent){
        when(event){
            is EditContactListEvent.PopToMainScreenFromEditScreen->{
                oneTimeEditEvent(EditContactUiEvent.AlwaysPopUPToMain(
                    ContactNavigationRoutes.MainScreen.route))
            }
            is EditContactListEvent.OnNameChange->{
                name = event.name
            }
            is EditContactListEvent.OnSurnameChange->{
                surname = event.surname
            }
            is EditContactListEvent.OnNumberChange->{
                number = event.number
            }
            is EditContactListEvent.OnPlaceChange->{
                place = event.place
            }
            is EditContactListEvent.OnEmailChange->{
                email = event.email
            }
            is EditContactListEvent.OnSaveContact->{
                viewModelScope.launch {
                    if(number == "0" || number.isBlank()){
                       // oneTimeEditEvent(EditContactUiEvent.ShowToastMessage("Number can't be empty"))
                        return@launch
                    }

                    getContactDatabaseRepository.addContact(
                        contact = Contact(
                            firstName = name,
                            lastName = surname,
                            number = number,
                            place = place,
                            email = email,
                            id =  contact?.id

                        ))
                    //Likely to be modified or remove
//                    oneTimeEditEvent(EditContactUiEvent
//                       .ShowToastMessage(message = "Contact saved"))

                    //Just added
                    oneTimeEditEvent(EditContactUiEvent.AlwaysPopUPToMain(ContactNavigationRoutes.MainScreen.route))

                }

            }
        }

    }
    private fun oneTimeEditEvent(event:EditContactUiEvent){
        viewModelScope.launch {
            _editContactUiEvent.send(event)
        }
    }


}
class EditContactFactory(private val appli:Application): AbstractSavedStateViewModelFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
       return ContactEditViewModel(appli, handle) as T
    }

}
