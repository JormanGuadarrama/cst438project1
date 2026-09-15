package com.example.cst438project1.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.cst438project1.R
import androidx.compose.runtime.State

class ProfileViewModel : ViewModel() {
    private val _selectedImage = mutableIntStateOf(R.drawable.profile_bunny)
    val selectedImage: State<Int> = _selectedImage
    private val _selectedColor = mutableStateOf(Color.Red)
    val selectedColor: State<Color> = _selectedColor
    fun updateImage(resId: Int) {
        _selectedImage.intValue = resId
    }
    fun updateColor(color: Color) {
        _selectedColor.value = color
    }
}