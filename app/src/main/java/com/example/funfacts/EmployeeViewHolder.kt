package com.example.funfacts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_employee.view.*

class EmployeeViewHolder(view:View) :RecyclerView.ViewHolder(view){
    var name=view.tx_item_name
    var age=view.tx_item_age
    var salary=view.tx_item_salary
}