package com.example.cst438project1

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.navigation.NavTree
import com.example.cst438project1.ui.theme.CST438Project1Theme
import androidx.core.content.edit
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // remembers users app settings even after the app is closed
        val preferences = getSharedPreferences("app_settings", MODE_PRIVATE)

        // checks to see if user is using dark mode and makes it the default
        val systemDarkMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK ==
                Configuration.UI_MODE_NIGHT_YES
        var isDarkTheme by mutableStateOf(
            preferences.getBoolean("dark_theme", systemDarkMode)
        )

        setContent {
            CST438Project1Theme(
                darkTheme = isDarkTheme,
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavTree(
                        navController,
                        isDarkTheme = isDarkTheme,
                        onThemeChange = { useDarkTheme ->
                            isDarkTheme = useDarkTheme

                            preferences.edit {
                                putBoolean("dark_theme", useDarkTheme)
                            }
                        }
                    )
                }
            }
        }
    }
}
