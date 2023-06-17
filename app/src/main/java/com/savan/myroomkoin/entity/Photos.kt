package com.savan.myroomkoin.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "photos_table")
data class PhotosItem(

    @ColumnInfo(name = "albumId")
    val albumId: Int,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String
)
