package com.www.mypets.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx. compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.www.mypets.viewmodel.PetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetListScreen(
    viewModel: PetViewModel,
    onAddPetClick: () -> Unit // handle navigation or screen switch
){
    val pets by viewModel.allPets.collectAsState()
    // viewModel.allPets is a StateFlow<List<Pet>>` (reactive stream of data from your ViewModel).
    //collectAsState() is a Jetpack Compose extension function that:
        //-Subscribes to the StateFlow
        //-Collects its current value
        //-Triggers recomposition whenever the value changes

    Scaffold(
        topBar = {
            TopAppBar(title = {Text("My Pets")})
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPetClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pet")
            }
        }
    ){ innerPadding ->
    LazyColumn( //LazyColumn is a Jetpack Compose component used to display a vertically scrollable list of items, similar to how RecyclerView works in the old Android View system.
        contentPadding = innerPadding,
        modifier = Modifier.padding(8.dp)
    ) {
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

}