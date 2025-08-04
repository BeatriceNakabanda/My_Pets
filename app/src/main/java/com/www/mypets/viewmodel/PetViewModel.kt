package com.www.mypets.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.www.mypets.data.Pet
import com.www.mypets.data.PetDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PetViewModel(application: Application): AndroidViewModel(application) {
    private val petDao = PetDatabase.getDatabase(application).petDao()
    val allPets: StateFlow<List<Pet>> = petDao.getAllPets().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
    //1. Calls getAllPets() from your PetDao, which returns a Flow<List<Pet>>.
    //2. Converts that Flow into a StateFlow<List<Pet>> using //.stateIn(...).
    //3.Makes it lifecycle-aware and immediately consumable by UI (e.g., in Jetpack Compose).

    //Why use StateFlow<List<Pet>>?
    //1.Holds the latest UI state (e.g. a list of pets).
    //2. Automatically updates UI when data changes.
    //3. Thread-safe and lifecycle-aware (when used properly).
    //4.Works well with Jetpack Compose and coroutines.
    //StateFlow<List<Pet>> is used to hold and expose a real-time, observable list of pets, which can be updated and safely shared with the UI

    fun addPet(name: String, breed: String, age: Int){
        viewModelScope.launch { // a special coroutine scope tied to the lifecycle of a ViewModel. When the ViewModel is cleared (e.g. the screen is destroyed), any coroutines launched in this scope are automatically cancelled.
            petDao.addPet(Pet(name = name, breed = breed, age = age))
        }
    }
}