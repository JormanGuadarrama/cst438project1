package com.example.cst438project1.ui.viewmodel

import android.app.Application
import android.icu.text.StringSearch
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cst438project1.data.local.AppDatabase
import com.example.cst438project1.data.local.UserDao
import com.example.cst438project1.data.local.UserEntity
import com.example.cst438project1.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application): AndroidViewModel(application) {
    private val dao= AppDatabase.getInstance(application).userDao()
    private val repository= UserRepository(dao)
    private val _currentUser = MutableStateFlow<UserEntity?>(null)
    val currentUser: StateFlow<UserEntity?> = _currentUser
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage
    fun login(username: String, password: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            val user=repository.getUserByUsername(username)
            if(user==null){
                _errorMessage.value = "User not found"
                return@launch
            }
            if(user.password !=password){
                _errorMessage.value = "Password is Incorrect"
                return@launch
            }
            _errorMessage.value=""
            _currentUser.value=user
            onSuccess()
        }
    }
    fun signUp(username: String, password: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            val existing=repository.getUserByUsername(username)
            if(existing != null){
                _errorMessage.value = "Username already exists"
                return@launch
            }
            val defaultImage=listOf(
                com.example.cst438project1.R.drawable.profile_duck,
                com.example.cst438project1.R.drawable.profile_cat,
                com.example.cst438project1.R.drawable.profile_bunny,
                com.example.cst438project1.R.drawable.profile_otter
            ).random()
            val defaultColor=listOf(
                Color.Black.value.toLong(),
                Color.Blue.value.toLong(),
                Color.Green.value.toLong(),
                Color.White.value.toLong(),
                Color.Red.value.toLong(),
                Color.Yellow.value.toLong(),
                Color.Cyan.value.toLong(),
                0xFFE0B0FF
            ).random()
            val newUser= UserEntity(
                username = username,
                password = password,
                profileImage = defaultImage,
                profileColor = defaultColor,
                recentSearch = ""
            )
            repository.insertUser(newUser)
            val inserted = repository.getUserByUsername(username)
            _currentUser.value= inserted
            _errorMessage.value= ""
            onSuccess()
        }
    }
    fun updateProfileImage(newImage: Int){
        val user = _currentUser.value ?:return
        val updated= user.copy(profileImage = newImage)
        viewModelScope.launch {
            repository.updateUser(updated)
            _currentUser.value= updated
        }
    }
    fun updateProfileColor(newColor: Color){
        val user = _currentUser.value ?: return
        val updated = user.copy(profileColor = newColor.toArgb().toLong())
        viewModelScope.launch {
            repository.updateUser(updated)
            _currentUser.value = updated
        }
    }
    fun setError(msg: String) {
        _errorMessage.value = msg
    }
    fun updateUsername(newUsername: String) {
        val user = _currentUser.value ?: return
        viewModelScope.launch {
            val existing = repository.getUserByUsername(newUsername)
            if (existing != null) {
                _errorMessage.value = "Username already exists"
                return@launch
            }
            repository.updateUsername(user.id, newUsername)
            _currentUser.value = user.copy(username = newUsername)
            _errorMessage.value = ""
        }
    }
    fun updatePassword(newPassword: String) {
        val user = _currentUser.value ?: return
        viewModelScope.launch {
            repository.updatePassword(user.id, newPassword)
            _currentUser.value = user.copy(password = newPassword)
            _errorMessage.value = ""
        }
    }
    fun addRecentSearch(userId: Int, newSearch: String){
        viewModelScope.launch {
            val user= dao.getUserById(userId) ?: return@launch
            val currentList= user.recentSearch.split(",").filter { it.isNotBlank() }
            val updateList=listOf(newSearch)+currentList
            val trimmedList =updateList.take(5)
            val finalString = trimmedList.joinToString(",")
            dao.updateRecentSearch(userId,finalString)
            _currentUser.value = user.copy(recentSearch = finalString)
        }
    }
    fun setCurrentUser(user: UserEntity?) {
        _currentUser.value = user
    }
}