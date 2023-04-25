package com.example.mycontacts.ContactsData

import android.content.Context
import androidx.compose.animation.core.animateIntAsState
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase:RoomDatabase() {
    abstract fun getContactDoa():ContactDoa

    companion object{

        @Volatile
        private var DATABASE_INSTANCE:ContactDatabase? = null

        fun databaseInitializer(context: Context):ContactDatabase{
            return DATABASE_INSTANCE?: synchronized(this){
                val inst = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contactDatabase"
                ).build()
                DATABASE_INSTANCE = inst
                inst
            }

        }

    }

}



