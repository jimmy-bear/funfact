package com.example.funfacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_employee_list.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EmployeeListFragment private constructor(): Fragment() {

    lateinit var recycle:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_employee_list, container, false)
        recycle=view.findViewById(R.id.recycleEmployee)
        // Inflate the layout for this fragment
        CoroutineScope(Dispatchers.Main).launch {

        }
        return view
    }

    companion object {
        val instance by lazy {
            EmployeeListFragment()
        }
    }
}