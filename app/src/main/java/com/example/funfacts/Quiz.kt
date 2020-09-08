package com.example.funfacts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "quizTable")
data class Quiz(
    //自定義格式
    @ColumnInfo(name = "Question")
    val question:String="",
    @ColumnInfo(name = "Answer")
    val answers:Int=0
){
//    @Ignore
//    var option:List<Option> = listOf()
    //主鍵
    @PrimaryKey
    var id:Long?=null

}
@Entity(tableName = "options")
data class Option(
    val option:String="",
    @ColumnInfo(name = "optionId")
    val optionId:Long=0
    ){
    @PrimaryKey
    var id: Long?=null
}