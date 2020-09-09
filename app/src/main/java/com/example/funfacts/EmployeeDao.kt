package com.example.funfacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(employeeList: List<Employee>):List<Long>

    @Query("Select*from Employee")
    fun getData():List<Employee>
}