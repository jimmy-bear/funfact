package com.example.funfacts

import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val TAG=MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
        //readQuestion()
        //只有第一次沒表格，它會產生
        //所以要更改寫法

//        Thread(
//            Runnable {
//
//
//            }
//        ).start()


    }

    private fun test() {
        val db=QuizDatabase.getInstance(this)

        val question= listOf<String>("Bear color","TV Show","Book Type")
        val answer= listOf<Int>(0,1,2)
        val option= listOf<Option>(Option("yellow",0), Option("Blue",0),
            Option("white",0), Option("red",0)
        )
        val option1= listOf<Option>(Option("Blue",1), Option("Blue",1),
            Option("Blue",1), Option("Blue",1))

        val option2=listOf<Option>(Option("white",2), Option("white",2),
            Option("white",2), Option("white",2))
        val options= listOf<List<Option>>(option,option1,option2)
        val optionId= listOf<Long>(0,1,2)
        CoroutineScope(Dispatchers.IO).launch {
            for (i  in 0 until options.size) {
                db.quizDao().run {
                    add(Quiz(question[i], answer[i]))
                    insertOption(options[i])
                    getOptionByID(optionId[i])
                }
            }
            withContext(Dispatchers.Main){

            }
        }
//        thread {
//            runOnUiThread {
//
//            }
//        }
    }

    private fun readQuestion() {

        val data=resources.openRawResource(R.raw.questions).bufferedReader().readText()
        val question=JSONArray(data)
        for (i in 0..question.length()-1){
            val obj=question.getJSONObject(i)
            val q=obj.getString("question")
            val a=obj.getInt("answer")
            val o=obj.getJSONArray("option")
            val bag= mutableListOf<String>()
            Log.d(TAG, "readQuestion: $q")
            for (j in 0..o.length()-1){
                Log.d(TAG, "readOption: ${o.getString(j)}")
                bag.add(o.getString(j))
            }
            CoroutineScope(Dispatchers.IO).launch {
                QuizDatabase.getInstance(this@MainActivity)
                    .quizDao().apply {
                        val quizID=add(Quiz("bear",0))
                    }
                withContext(Dispatchers.Main){

                }
            }
            thread {
                val db=QuizDatabase.getInstance(this)
                val id=db.quizDao().add(Quiz(q,a))
            }

        Log.d(TAG, "readAnswer : $a")
        }
    }
}