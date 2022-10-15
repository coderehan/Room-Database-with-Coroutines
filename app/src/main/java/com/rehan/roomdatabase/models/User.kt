package com.rehan.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(val name: String, val age: Int) {

    @PrimaryKey(autoGenerate = true)
    private val id: Int? = null

}
