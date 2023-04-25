package com.example.mycontacts.ContactsData

import android.app.Application
import android.content.Context

class ContactDatabaseRepository(application: Application) {
    private val repository = ContactDatabase.databaseInitializer(context = application).getContactDoa()

    suspend fun addContact(contact: Contact){
        repository.addContact(contact)
    }
    suspend fun deleteContact(contact: Contact){
        repository.deleteContact(contact)
    }
    suspend fun getContactId(contact:Int):Contact?{
       return repository.findContactId(contact)
    }
    val displayContact = repository.displayContacts()

}
