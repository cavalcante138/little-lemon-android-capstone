package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("LittleLemonPrefs", Context.MODE_PRIVATE)
    val firstName = sharedPref.getString("firstName", "") ?: ""
    val lastName = sharedPref.getString("lastName", "") ?: ""
    val email = sharedPref.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val logo: Painter = painterResource(id = R.drawable.logo)
        Image(
            painter = logo,
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Text(
            text = "Profile information:",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(text = "First Name: $firstName", style = MaterialTheme.typography.body1)
        Text(text = "Last Name: $lastName", style = MaterialTheme.typography.body1)
        Text(text = "Email: $email", style = MaterialTheme.typography.body1)
        Button(
            onClick = {
                sharedPref.edit().clear().apply()
                navController.navigate(Destinations.Onboarding) {
                    popUpTo(Destinations.Home) { inclusive = true }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Log out")
        }
    }
}