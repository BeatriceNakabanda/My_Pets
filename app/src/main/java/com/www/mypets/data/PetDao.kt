package com.www.mypets.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao //Data Access Object- tell Room that this interface or class is used to access the database.o get, add, update, or delete data from the database
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPet(pet: Pet)
//    suspend fun can pause (or “suspend”) its execution without blocking the thread and resume later

    @Query("SELECT * FROM pets")
    fun getAllPets(): Flow<List<Pet>> //Flow- give me a stream of pet data and keep updating it every time the data changes.
    //gives a list of pets (from the database),
    //Automatically updates the UI whenever that list changes (e.g. after adding or deleting a pet).
}