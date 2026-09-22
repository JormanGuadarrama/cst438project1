package com.example.cst438project1.data.repository

import com.example.cst438project1.data.local.UserDao
import com.example.cst438project1.data.local.UserEntity

class UserRepository(private val dao: UserDao) {
    suspend fun insertUser(user: UserEntity){
        dao.insertUser(user)
    }
    suspend fun getUserByUsername(username: String): UserEntity?{
        return dao.getUserByUsername(username)
    }
    suspend fun updateUser(user: UserEntity){
        dao.updateUser(user)
    }
    suspend fun updateRecentSearch(userId: Int,search:String){
        dao.updateRecentSearch(userId,search)
    }
    suspend fun deleteUser(userId: Int) {
        dao.deleteUser(userId)
    }
    //for setting to change username
    suspend fun updateUsername(userId: Int, newUsername: String){
        dao.updateUsername(userId,newUsername)
    }
    //for setting to change password
    suspend fun updatePassword(userId: Int,newPassword: String){
        dao.updatePassword(userId,newPassword)
    }
}