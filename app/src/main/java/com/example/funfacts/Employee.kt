package com.example.funfacts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(val name:String="",
                    val salary:Int=25000,
                    val age:Int=18){
    @PrimaryKey
    var id:Long?=null
}