package com.www.mypets.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.www.mypets.viewmodel.PetViewModel

@Composable // It can describe UI in a declarative way.
fun AddPetScreen(viewModel: PetViewModel, onPetAdded: () -> Unit) {
    var name by remember { mutableStateOf("") } //remember-Keeps the state across recompositions..
    // mutableStateOf- Recomposes the UI when the value changes
    //by - Kotlin delegation — so you can use name instead of //name.value
    var breed by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = name, onValueChange = {name = it}, label = { Text("Enter Pent Name") })
        TextField(value = breed, onValueChange = {breed = it}, label = {Text("Enter Breed")})
        TextField(value = age, onValueChange = {age = it}, label = {Text("Enter Age")})

        Button(onClick = {
            if (name.isNotBlank() && breed.isNotBlank() && age.isNotBlank()){
                viewModel.addPet(name, breed, age.toInt())
                onPetAdded()
            }
        }) { Text("Add Pet") }
    }
}