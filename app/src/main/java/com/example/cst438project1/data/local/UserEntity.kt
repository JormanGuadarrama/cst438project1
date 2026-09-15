package com.example.cst438project1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val profileImage: Int,
    val profileColor: Long,
    val recentSearch: String=""
)
