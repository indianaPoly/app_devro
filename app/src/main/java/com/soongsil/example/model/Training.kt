package com.soongsil.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Training(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val check : Boolean,
    val content : String,
    var year : Int, var month : Int, var day : Int
)
