package com.example.funfacts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Employee::class),version = 1,exportSchema = false)
abstract class EmployeeDataBase :RoomDatabase(){

    abstract fun employeeDao():EmployeeDao
    companion object{
        private var instance:EmployeeDataBase?=null

        fun getInstance(context:Context):EmployeeDataBase{
            if(instance==null){
                instance=Room.databaseBuilder(context,EmployeeDataBase::class.java,"employee.db").build()
            }
            return instance!!
        }
    }

}