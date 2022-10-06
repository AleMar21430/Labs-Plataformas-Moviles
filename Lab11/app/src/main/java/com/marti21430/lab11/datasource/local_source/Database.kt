package com.marti21430.lab11.datasource.local_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDAO::class], version = 1)

abstract class Database: RoomDatabase() {
    abstract fun UserDAO(): UserDAO
}