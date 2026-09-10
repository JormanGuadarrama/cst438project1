package com.example.cst438project1.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlaceholderDao {
    @Insert
    suspend fun insert(entity: PlaceholderEntity)

    @Query("SELECT * FROM placeholder")
    suspend fun getAll(): List<PlaceholderEntity>
}
