package com.example.mycontacts.ContactsData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface  ContactDoa {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
    //@SuppressWarning room complains that should be fixed,
    //but developer knows what he is doing, so he suppress it.
   // @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM contact")
    fun displayContacts(): Flow<List<Contact>>

    // @Query("SELECT * FROM song WHERE id IN(:songIds)")
    @Query("SELECT * FROM Contact WHERE id = :contact")
    suspend fun findContactId(contact: Int):Contact?

}

