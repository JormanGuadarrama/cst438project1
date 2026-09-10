package com.example.cst438project1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun UserProfileScreen(navController: NavHostController,viewModel: ProfileViewModel){
    //TODO add user details DB
    //fake testing
    val username="Jess"
    val searchHistory= listOf(
        //TODO: add fromFM DB and User DB
        "Music Search 1",
        "Music Search 2",
        "Music Search 3"
    )
    //replace later when creating better veiw model
    val selectedImage= viewModel.selectedImage.value
    val selectedColor= viewModel.selectedColor.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Color(0xFF57A0D2)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(selectedColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(selectedImage),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text=username, fontSize = 25.sp)
        Button(
            onClick = {
                navController.navigate("profilepic")
            }, modifier =
                Modifier.fillMaxWidth()
                    .padding(horizontal = 40.dp)
        ) {
            Text("Change Profile Picture")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(text="Recent Searches", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            searchHistory.forEach { item->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE0E0E0))
                        .padding(12.dp)
                ) {
                    Text(text=item)
                }
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