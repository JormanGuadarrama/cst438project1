package com.example.cst438project1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.ui.theme.CST438Project1Theme
import com.example.cst438project1.ui.screens.ProfileViewModel

@Composable
fun HomeScreen(navController: NavController,viewModel: ProfileViewModel){
    var searchField by remember {
        mutableStateOf("")
    }
    val selectedImage by viewModel.selectedImage
    val selectedColor by viewModel.selectedColor

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
            text = "Home",
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Row(
            modifier = Modifier.align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            IconButton(
                onClick = {

                }
            ){
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }

            IconButton(
                onClick = {
                    navController.navigate("userprofilescreen")
                }
            ){
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(selectedColor),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(selectedImage),
                        contentDescription = "Profile",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value = searchField,
                onValueChange = { text ->
                    searchField = text
                },
                label = {
                    Text("Search")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {

                        }
                    ){
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true
            )
        }
    }
}