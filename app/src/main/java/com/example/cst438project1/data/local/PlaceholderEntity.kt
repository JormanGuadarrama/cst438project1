package com.example.cst438project1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Throwaway entity proving the Room/KSP pipeline builds; delete once a real entity exists pls 
@Entity(tableName = "placeholder")
data class PlaceholderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val note: String
)
