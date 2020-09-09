package com.example.funfacts

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var jsonUrl:String
    private val TAG=MainActivity::class.java.simpleName
    private lateinit var adapterE: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            jsonUrl = URL("http://dummy.restapiexample.com/api/v1/employees").readText()
            readEmployee()
            Log.d(TAG, "onCreate: $jsonUrl")
            val db=EmployeeDataBase.getInstance(this@MainActivity)
            val list=db.employeeDao().getData()
            withContext(Dispatchers.Main){
                adapterE=EmployeeAdapter(list)
                recycle_employee.run {
                    setHasFixedSize(true)
                    layoutManager=LinearLayoutManager(this@MainActivity)
                    adapter=adapterE
                }
            }
        }
    }

    private fun readEmployee() {
        val db=EmployeeDataBase.getInstance(this)

        val json = JSONObject(jsonUrl)
        val data = json.getJSONArray("data")
        val dataList= mutableListOf<Employee>()
        for (i in 0 until data.length()) {
                val obj = data.getJSONObject(i)
                val name = obj.getString("employee_name")
                val salary = obj.getInt("employee_salary")
                val age = obj.getInt("employee_age")
                Log.d(TAG, "readEmployee: $name+$salary+$age")
                dataList.add(Employee(name, salary, age))
        }
        db.employeeDao().add(dataList)

    }
    private class EmployeeAdapter(var dataList: List<Employee>) :RecyclerView.Adapter<EmployeeViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.item_employee,parent,false)
            return EmployeeViewHolder(view)
        }

        override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

            holder.name.setText(dataList[position].name)
            holder.age.setText(dataList[position].age.toString())
            holder.salary.setText(dataList[position].salary.toString())
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

    }

}