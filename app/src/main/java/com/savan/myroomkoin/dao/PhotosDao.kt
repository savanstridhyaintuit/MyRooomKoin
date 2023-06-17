package com.savan.myroomkoin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.savan.myroomkoin.entity.PhotosItem
@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhotos(employee: PhotosItem)

    @Query("SELECT * FROM photos_table")
    fun getPhotos() : List<PhotosItem>
}