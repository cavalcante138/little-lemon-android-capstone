package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text(text = "Home")
        val profileIcon: Painter = painterResource(id = R.drawable.profile)
        Image(
            painter = profileIcon,
            contentDescription = "Profile Icon",
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { navController.navigate(Destinations.Profile) }
        )
    }
}
