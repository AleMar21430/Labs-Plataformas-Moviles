package com.marti21430.lab11.datasource.local_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val origin: String,
    val episodes: Int
)
