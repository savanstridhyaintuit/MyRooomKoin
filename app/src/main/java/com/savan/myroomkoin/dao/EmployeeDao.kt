package com.savan.myroomkoin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.savan.myroomkoin.entity.Note

@Dao
  interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEmployee(employee: Note)

    @Query("SELECT * FROM employees")
     fun getEmployee() : List<Note>

}