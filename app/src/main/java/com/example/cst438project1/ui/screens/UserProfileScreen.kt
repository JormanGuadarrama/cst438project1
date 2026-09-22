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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cst438project1.ui.viewmodel.AuthViewModel
import com.example.cst438project1.ui.viewmodel.ProfileViewModel

@Composable
fun UserProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel,
    authViewModel: AuthViewModel
){
    val user= authViewModel.currentUser.collectAsState().value
    val username=user?.username ?:"User"
    val selectedImage = user?.profileImage ?: profileViewModel.selectedImage.value
    val selectedColor = user?.profileColor?.let {Color(it)} ?: profileViewModel.selectedColor.value
    val searchHistory= user?.recentSearch?.split(",")?.filter { it.isNotBlank() }?: emptyList()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFF151C62)),
            contentAlignment = Alignment.Center
        ) {
           Box(
               modifier = Modifier
                   .size(140.dp)
                   .clip(CircleShape)
                   .background(selectedColor),
               contentAlignment = Alignment.Center
           ) {
               Image(painter= painterResource(selectedImage),
                   contentDescription = "Profile Pic",
                   modifier= Modifier.size(110.dp))
           }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text=username, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))

        Text(text="Recent Searches",fontSize= 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        if(searchHistory.isEmpty()){
            Text(
                text="No recent searches",
                fontSize = 16.sp,
                color= Color.Gray
            )
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(searchHistory) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFE0E0E0))
                            .padding(12.dp)
                    ) {
                        Text(text = item, color=Color.Black)
                    }
                    HorizontalDivider()
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {navController.navigate("profilepic")},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Change Profile Picture")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {navController.navigate("settings")},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Settings")
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text="Back",
            color= MaterialTheme.colorScheme.primary,
            fontSize = 15.sp,
            modifier = Modifier.clickable{
                navController.navigate("home")
            }
        )
    }
}