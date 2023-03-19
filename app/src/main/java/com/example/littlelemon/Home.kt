package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavHostController, viewModel: List<MenuItemEntity>, searchPhrase: String, onSearchPhraseChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val profileIcon: Painter = painterResource(id = R.drawable.profile)
        Image(
            painter = profileIcon,
            contentDescription = "Profile Icon",
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { navController.navigate(Destinations.Profile) }
        )

        Text(text = "Little Lemon", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = "Chicago", fontSize = 18.sp)
        Text(text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist", fontSize = 14.sp)


        TextField(
            value = searchPhrase,
            onValueChange = onSearchPhraseChange,
            label = { Text("Enter search phrase") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            singleLine = true
        )

        val categories = listOf("starters", "desserts", "mains")

        val selectedCategory = remember { mutableStateOf("") }

        Row {
            categories.forEach { category ->
                Button(
                    onClick = { selectedCategory.value = category },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(category)
                }
            }
        }


        MenuItems(viewModel.filter { menuItem ->
            (selectedCategory.value.isEmpty() || menuItem.category == selectedCategory.value) &&
                    (searchPhrase.isEmpty() || menuItem.title.contains(searchPhrase, ignoreCase = true))
        })

    }
}


@Composable
fun MenuItems(itemsList: List<MenuItemEntity>) {
    LazyColumn {
        items(itemsList) { menuItem ->
            MenuItem(menuItem = menuItem)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItemEntity) {
    Column {
        Text(text = menuItem.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = menuItem.description, fontSize = 14.sp)
        Text(text = "$${menuItem.price}", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        GlideImage(
            model = menuItem.image,
            contentDescription = "Menu item image",
        )
    }
}
