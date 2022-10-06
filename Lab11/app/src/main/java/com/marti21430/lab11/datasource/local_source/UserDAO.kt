package com.marti21430.lab11.datasource.local_source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.marti21430. lab11.datasource.local_source.User


interface UserDAO {
    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUser(): User

    @Query("DELETE FROM user")
    suspend fun deleteAll(): Int

    @Delete
    suspend fun delete(user: User): Int

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)
}