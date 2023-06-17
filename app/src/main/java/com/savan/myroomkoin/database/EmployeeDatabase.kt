package com.savan.myroomkoin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.savan.myroomkoin.dao.EmployeeDao
import com.savan.myroomkoin.dao.PhotosDao
import com.savan.myroomkoin.entity.Note
import com.savan.myroomkoin.entity.PhotosItem

@Database(entities = [Note::class,PhotosItem::class], version = 2, exportSchema = false)

abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun empDao(): EmployeeDao
    abstract fun photoDao():PhotosDao
}