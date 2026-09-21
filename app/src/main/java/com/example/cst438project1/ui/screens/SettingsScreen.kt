package com.example.cst438project1.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cst438project1.ui.theme.CST438Project1Theme
import com.example.cst438project1.ui.viewmodel.AuthViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    authViewModel: AuthViewModel? = null
) {
    var showPasswordDialog by remember {
        mutableStateOf(false)
    }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var newPassword by remember {
        mutableStateOf("")
    }

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
            text = "Account",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = {
                showPasswordDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("change_password_button")
        ) {
            Text("Change Password")
        }

        Button(
            onClick = {
                showDeleteDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("delete_account_button")
        ) {
            Text("Delete Account")
        }

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

    if(showPasswordDialog) {
        AlertDialog(
            onDismissRequest = {
                showPasswordDialog = false
            },
            title = {
                Text("Change Password")
            },
            text = {
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = {
                        newPassword = it
                    },
                    label = {
                        Text("New Password")
                    },
                    modifier = Modifier.testTag("new_password_field")
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if(newPassword.isNotBlank()) {
                            authViewModel?.updatePassword(newPassword)
                            newPassword = ""
                            showPasswordDialog = false
                        }
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showPasswordDialog = false
                        newPassword = ""
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    if(showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            title = {
                Text("Delete Account")
            },
            text = {
                Text("Are you sure you want to delete your account?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        authViewModel?.deleteAccount {
                            navController.navigate("landing") {
                                popUpTo("home") {
                                    inclusive = true
                                }
                            }
                        }

                        showDeleteDialog = false
                    }
                ) {
                    Text(
                        text = "Delete",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
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