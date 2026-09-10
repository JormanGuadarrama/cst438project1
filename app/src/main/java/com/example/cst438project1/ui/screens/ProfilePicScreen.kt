package com.example.cst438project1.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.R
import com.example.cst438project1.ui.theme.CST438Project1Theme



@Composable
fun ProfilePicScreen(navController: NavHostController,viewModel: ProfileViewModel){
    var selectedImage by remember{
        mutableStateOf(R.drawable.profile_otter)
    }
    var selectedColor by remember {
        mutableStateOf(Color.Cyan)
    }
    val images= listOf(
        R.drawable.profile_cat,
        R.drawable.profile_duck,
        R.drawable.profile_bunny,
        R.drawable.profile_otter
    )
    val colors= listOf(
        Color.Black,
        Color.Blue,
        Color.Green,
        Color.White,
        Color.Red,
        Color.Yellow,
        Color.Cyan,
        Color(0xFFE0B0FF)
    )
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(selectedColor),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(selectedImage),
                contentDescription = null,
                modifier = Modifier.size(120.dp).clip(CircleShape)
            )
        }
        Spacer(
            Modifier.height(30.dp)
        )
        Text("Select a Profile Image")
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            images.forEach {
                img ->
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .border(
                            width = if (img==selectedImage) 3.dp else 0.dp,
                            color = if(img==selectedImage) Color.Black else Color.Transparent,
                            shape=CircleShape
                        ).clickable { selectedImage = img },
                            contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(img),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )

                }
            }
        }
        Spacer(
            Modifier.height(20.dp)
        )
        Text("Select background color")
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            colors.forEach {
                    color ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(color)
                        .border(
                            width = 3.dp,
                            color = if (selectedColor == color) Color.Magenta else Color.Black,
                            shape= RoundedCornerShape(6.dp)
                        ).clickable{selectedColor=color}
                )
            }
        }
        Text(
            text="Back",
            color = Color.Blue,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top=20.dp)
                .clickable{
                    navController.popBackStack()
                }
        )
    }
}

