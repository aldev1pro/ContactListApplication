package com.example.mycontacts.ContactBusinessLayer.DetailScreenBusinessData

import android.app.Application
import android.widget.TextView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.ContactMainViewModel
import com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData.ContactNavigationRoutes
import com.example.mycontacts.ContactsData.Contact
import com.example.mycontacts.ContactsData.ContactDatabaseRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ContactDetailViewModel(appli:Application, savedState:SavedStateHandle):ViewModel() {

    private val getContactDatabaseRepository = ContactDatabaseRepository(appli)

    private var _detailContactUiEvent = Channel<DetailContactUiEvent>()
    val detailContactUiEvent = _detailContactUiEvent.receiveAsFlow()

    var restoreContact:Contact? by mutableStateOf(null)
    private set

    var firstname by mutableStateOf("")
    private set

    var surname by mutableStateOf("")
    private set

    var number by mutableStateOf("")
    private set

    var place by mutableStateOf("")
    private set

    var email by mutableStateOf("")
    private set



    init{
        val getContact = savedState.get<Int>("contactId")
        if(getContact != -1){
            viewModelScope.launch {
                if (getContact != null) {
                    getContactDatabaseRepository.getContactId(getContact)?.let { contact ->

                        firstname = contact.firstName.toString()
                        surname = contact.lastName.toString()
                        number = contact.number
                        place = contact.place.toString()
                        email = contact.email.toString()
                        this@ContactDetailViewModel.restoreContact = contact

                    }
                }
            }
        }
    }
    var deletedContact:Contact? = null

    fun detailEvents(event:DetailContactListEvent){
        when(event){
            is DetailContactListEvent.OnEditScreenNavigate -> {
                oneTimeEvent(DetailContactUiEvent.NavigateToEditContactScreenWithData(
                    ContactNavigationRoutes.EditContactScreen.route + "?contactId=${event.contact.id}"))
            }
            is DetailContactListEvent.OnDeleteContact -> {
                viewModelScope.launch {
                    deletedContact = event.contact
                    getContactDatabaseRepository.deleteContact(event.contact)

                    oneTimeEvent(DetailContactUiEvent.ShowSnackbar(
                        message = "Contact deleted",
                        action = "Undo"
                    ))
                    //oneTimeEvent(DetailContactUiEvent.PopToMainScreenContact)
                    oneTimeEvent(DetailContactUiEvent.NavigateToEditContactScreenWithData
                        (ContactNavigationRoutes.MainScreen.route)
                    )

                }

            }
            is DetailContactListEvent.OnUndoContact -> {
                viewModelScope.launch {
                    deletedContact?.let { contact->
                        getContactDatabaseRepository.addContact(contact)
                    }
                }
            }
            is DetailContactListEvent.OnBackPressToMainContact -> {
                oneTimeEvent(DetailContactUiEvent.NavigateToEditContactScreenWithData(ContactNavigationRoutes.MainScreen.route))
               // oneTimeEvent(DetailContactUiEvent.PopToMainScreenContact)
            }
        }
    }

    private fun oneTimeEvent(uiEvent:DetailContactUiEvent){
        viewModelScope.launch {
            _detailContactUiEvent.send(uiEvent)
        }
    }

}
class DetailFactory(private val appli: Application):AbstractSavedStateViewModelFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        return ContactDetailViewModel(appli, handle) as T
    }

}
