package com.www.mypets.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx. compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.www.mypets.viewmodel.PetViewModel

@Composable
fun MyPetsApp(viewModel: PetViewModel) {
    var showAddPetScreen by remember { mutableStateOf(false) }
    //remember { ... }- Keeps this state across recompositions (but not across process death or rotation)
    //mutableStateOf(false)-Creates a reactive state with an initial value of false
    if (showAddPetScreen){
        AddPetScreen(viewModel = viewModel ) {
            showAddPetScreen = false
        }
    } else {
        Column{
            Button(onClick = {showAddPetScreen = true}) {
                Text("Add Pet")
            }
            PetListScreen(viewModel = viewModel)
        }
    }
}