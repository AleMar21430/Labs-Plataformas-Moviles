package com.marti21430.lab11.datasource.local_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marti21430.lab11.datasource.local_source.User

@Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}