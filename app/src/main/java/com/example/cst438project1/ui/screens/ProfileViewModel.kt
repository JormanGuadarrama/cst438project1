package com.example.cst438project1.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import com.example.cst438project1.R

class ProfileViewModel : ViewModel() {
    private val _selectedImage = mutableStateOf(R.drawable.profile_bunny)
    val selectedImage: State<Int> = _selectedImage
    private val _selectedColor = mutableStateOf(Color.Red)
    val selectedColor: State<Color> = _selectedColor
    fun updateImage(resId: Int) {
        _selectedImage.value = resId
    }
    fun updateColor(color: Color) {
        _selectedColor.value = color
    }
}