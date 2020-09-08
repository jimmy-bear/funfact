package com.example.funfacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(quiz: Quiz):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOption(answer:List<Option>):Array<Long>

    @Query("select * from options where optionId=:optionId")
    fun getOptionByID(optionId:Long):List<Option>

    @Query("select * from quizTable")
    fun getAll():List<Quiz>


}