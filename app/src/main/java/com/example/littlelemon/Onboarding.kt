package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun Onboarding(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("LittleLemonPrefs", Context.MODE_PRIVATE)
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val logo: Painter = painterResource(id = R.drawable.logo)
        Image(
            painter = logo,
            contentDescription = "App Logo",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            contentScale = ContentScale.Fit
        )
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.Green)
                    .height(100.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Let's get to know you",
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {

                Text(
                    text = "First Name",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 16.dp)
                )
                TextField(
                    value = firstName.value,
                    onValueChange = { firstName.value = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Last Name",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 16.dp)
                )
                TextField(
                    value = lastName.value,
                    onValueChange = { lastName.value = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Email",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 16.dp)
                )
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { /* Do something on Done */ })
                )
                Button(
                    onClick = {
                        if (firstName.value.isNotBlank() && lastName.value.isNotBlank() && email.value.isNotBlank()) {
                            // Save user data to SharedPreferences
                            // Display "Registration successful!" message
                            with(sharedPref.edit()) {
                                putString("firstName", firstName.value)
                                putString("lastName", lastName.value)
                                putString("email", email.value)
                                apply()
                            }

                            navController.navigate(Destinations.Profile)
                        } else {
                            // Display "Registration unsuccessful. Please enter all data." message
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(bottom = 16.dp, top = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Register")
                }
            }

        }
    }
}