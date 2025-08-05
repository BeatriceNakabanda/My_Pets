package com.www.mypets.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.www.mypets.viewmodel.PetViewModel

@Composable // It can describe UI in a declarative way.
fun AddPetScreen(viewModel: PetViewModel, onPetAdded: () -> Unit) {
    var name by remember { mutableStateOf("") } //remember-Keeps the state across recompositions..
    // mutableStateOf- Recomposes the UI when the value changes
    //by - Kotlin delegation — so you can use name instead of //name.value
    var breed by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

//    Column(modifier = Modifier.padding(16.dp)) {
//        TextField(value = name, onValueChange = {name = it}, label = { Text("Enter Pent Name") })
//        TextField(value = breed, onValueChange = {breed = it}, label = {Text("Enter Breed")})
//        TextField(value = age, onValueChange = {age = it}, label = {Text("Enter Age")})
//
//        Button(onClick = {
//            if (name.isNotBlank() && breed.isNotBlank() && age.isNotBlank()){
//                viewModel.addPet(name, breed, age.toInt())
//                onPetAdded()
//            }
//        }) { Text("Add Pet") }
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF2EAF4), shape = RoundedCornerShape(12.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter Pet Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = breed,
                onValueChange = { breed = it },
                label = { Text("Enter Breed") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Enter Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.addPet(name, breed, age.toIntOrNull() ?: 0)
                    onPetAdded()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C4AB6)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Add Pet", color = Color.White)
            }
        }
    }
}