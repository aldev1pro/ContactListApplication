package com.example.mycontacts.ContactsData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Contact(
    @PrimaryKey val id:Int? = null,
    var firstName:String?,
    var lastName:String?,
    var number:String,
    val place:String?,
    var email:String?
)
