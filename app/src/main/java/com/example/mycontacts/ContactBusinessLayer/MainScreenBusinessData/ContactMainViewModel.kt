package com.example.mycontacts.ContactBusinessLayer.MainScreenBusinessData

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.mycontacts.ContactsData.ContactDatabaseRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ContactMainViewModel(appli:Application, private val savedState: SavedStateHandle):ViewModel() {

    private val getContactRepository = ContactDatabaseRepository(application = appli)

    var name by mutableStateOf(0)
    private set

    var displayContact = getContactRepository.displayContact


    private var _mainContactUiEvent = Channel<MainUiEvent>()
    val mainContactUiEvent = _mainContactUiEvent.receiveAsFlow()

    fun mainEvents(event: MainContactListEvent) {
        when (event) {
            is MainContactListEvent.OnAddContact -> {
                oneTimeContactEvent(MainUiEvent.NavigateToAnyScreen(route = ContactNavigationRoutes.EditContactScreen.route))
            }
            is MainContactListEvent.OnViewDetailContact -> {
                //savedState.set(key = "contactId",value = event.contact.id)
                name = event.contact.id!!
               // oneTimeContactEvent(MainUiEvent.NavigateToAnyScreen(route = ContactNavigationRoutes.DetailScreen.route + "?contactId=${event.contact.id}"))
                oneTimeContactEvent(MainUiEvent.NavigateToAnyScreen(route = ContactNavigationRoutes.DetailScreen.route + "?contactId=$name"))

            }
            is MainContactListEvent.OnSearchContact -> {
               // event.contact
               // Unit
            }
        }
    }

    private fun oneTimeContactEvent(ui: MainUiEvent) {
        viewModelScope.launch {
            _mainContactUiEvent.send(ui)
        }
    }
}
class MainContactFactory(private val appli: Application) : AbstractSavedStateViewModelFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
       // TODO("Not yet implemented")
        return ContactMainViewModel(appli ,handle ) as T
    }

}

//class MainContactFactory(private val appli:Application,private val savedState: SavedStateHandle):ViewModelProvider.NewInstanceFactory(){
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T = ContactMainViewModel(appli,savedState) as T
//
//}

