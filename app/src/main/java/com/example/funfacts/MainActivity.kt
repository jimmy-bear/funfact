package com.example.funfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val TAG=MainActivity::class.java.simpleName
    private lateinit var adapterE: EmployeeAdapter
    private lateinit var currentFragment:Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(States.UNLOGIN)

    }


    private fun setFragment(states:States) {
        when(states){
            States.UNLOGIN-> {
                supportFragmentManager.beginTransaction().run {
                    replace(R.id.container, LoginFragment.instance)
                    commit()
                }

                currentFragment = LoginFragment.instance
            }
            States.LOGIN->{
                supportFragmentManager.beginTransaction()
                    .run {
                        replace(R.id.container, EmployeeListFragment.instance)
                        commit()
                    }

                currentFragment = EmployeeListFragment.instance
                CoroutineScope(Dispatchers.IO).launch {
//              val data=URL("http://dummy.restapiexample.com/api/v1/employees").openConnection().getInputStream().bufferedReader()
//              jsonUrl=data.readLine()
                    handleData()
                    val db=EmployeeDataBase.getInstance(this@MainActivity)
                    val list=db.employeeDao().getData()
                    withContext(Dispatchers.Main){
                        setAdapter(list)
                    }
                }
            }
            States.SIGNUP->{
                supportFragmentManager.beginTransaction().run {
                    replace(R.id.container, SignUpFragment.instance)
                    commit()
                }
                currentFragment = SignUpFragment.instance
            }
        }
    }

    private fun setAdapter(list: List<Employee>) {
        adapterE=EmployeeAdapter(list)
        (currentFragment as EmployeeListFragment).recycle.run {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=adapterE
        }
    }

    fun login(view:View){
        val user=(currentFragment as LoginFragment).edUser.text.toString()
        val password=(currentFragment as LoginFragment).edPassword.text.toString()
        getSharedPreferences("Personal", MODE_PRIVATE).run {
            if(user==getString("user","")&&password==getString("password","")){
                setFragment(States.LOGIN)
            }else{
                AlertDialog.Builder(this@MainActivity)
                    .setMessage("User or Password Error ")
                    .show()
            }
        }
    }
    fun toSignup(view: View){
        setFragment(States.SIGNUP)
        Log.d(TAG, "toSignup: ")
    }

    fun signup(view: View){
        val user=(currentFragment as SignUpFragment).edUesr.text.toString()
        val password=(currentFragment as SignUpFragment).edPassword.text.toString()
        val email=(currentFragment as SignUpFragment).edEmail.text.toString()
        getSharedPreferences("Personal", MODE_PRIVATE).edit().run {
            putString("user",user)
            putString("password",password)
            putString("email",email)
            commit()
        }
        setFragment(States.UNLOGIN)
        Log.d(TAG, "signup: ")
    }

    private fun handleData() {
        val jsonU=resources.openRawResource(R.raw.employee).bufferedReader().readLine()
        val db=EmployeeDataBase.getInstance(this)
        val json = JSONObject(jsonU)
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
    enum class States{
        UNLOGIN,LOGIN,SIGNUP
    }
}

