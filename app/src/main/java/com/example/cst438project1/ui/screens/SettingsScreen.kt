package com.example.cst438project1.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.ui.theme.CST438Project1Theme
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme

@Composable
fun SettingsScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Settings",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = "Appearance",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        ThemeOption(
            text = "Light mode",
            selected = !isDarkTheme,
            tag = "light_mode_option",
            onClick = { onThemeChange(false) }
        )

        HorizontalDivider()

        ThemeOption(
            text = "Dark mode",
            selected = isDarkTheme,
            tag = "dark_mode_option",
            onClick = { onThemeChange(true) }
        )

        HorizontalDivider()

        Text(
            text = "Back",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    navController.navigate("home")
                }
                .testTag("settings_back")
        )
    }
}

@Composable
private fun ThemeOption(
    text: String,
    selected: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            modifier = Modifier.testTag(tag)
        )

        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    val navController = rememberNavController()

    CST438Project1Theme {
        SettingsScreen(
            navController = navController,
            isDarkTheme = false,
            onThemeChange = {}
        )
    }
}
