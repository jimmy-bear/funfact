package com.example.funfacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EmployeeAdapter(var dataList: List<Employee>) : RecyclerView.Adapter<EmployeeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_employee,parent,false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {

        holder.name.setText(dataList[position].name)
        holder.age.setText(dataList[position].age.toString())
        holder.salary.setText(dataList[position].salary.toString())
        holder.id.setText(dataList[position].id.toString())
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}