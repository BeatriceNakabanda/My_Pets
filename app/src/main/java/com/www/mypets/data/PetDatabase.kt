package com.www.mypets.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Pet::class], version = 1)
abstract class PetDatabase: RoomDatabase() {
    abstract fun petDao(): PetDao

    companion object { //Acts like a singleton holder for the PetDatabase. Ensures only one instance of the database exists during the app’s lifecycle.
        @Volatile private var INSTANCE: PetDatabase? = null
        //@Volatile ensures visibility of changes across threads.
        //INSTANCE holds the single instance of PetDatabase.

        fun getDatabase(context: Context): PetDatabase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    PetDatabase::class.java,
                    "pet_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}