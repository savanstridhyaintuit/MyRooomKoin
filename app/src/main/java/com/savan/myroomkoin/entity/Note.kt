package com.savan.myroomkoin.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Note(
    @PrimaryKey
    @ColumnInfo(name ="id")
     val id: Int,
    @ColumnInfo(name = "userId")
     val userId: String,
    @ColumnInfo(name = "title")
     val title: String
)
