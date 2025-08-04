package com.www.mypets.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx. compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.www.mypets.viewmodel.PetViewModel

@Composable
fun PetListScreen(viewModel: PetViewModel) {
    val pets by viewModel.allPets.collectAsState()
    // viewModel.allPets is a StateFlow<List<Pet>>` (reactive stream of data from your ViewModel).
    //collectAsState() is a Jetpack Compose extension function that:
        //-Subscribes to the StateFlow
        //-Collects its current value
        //-Triggers recomposition whenever the value changes

    LazyColumn {
        items(pets){ pet ->
            Card (
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ){
                Column (modifier = Modifier.padding(16.dp)){
                    Text("Name: ${pet.name}")
                    Text("Breed: ${pet.breed}")
                    Text("age: ${pet.age}")
                }
            }

        }
    }
}