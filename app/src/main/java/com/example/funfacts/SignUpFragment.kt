package com.example.funfacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class SignUpFragment private constructor(): Fragment() {

    lateinit var edUesr :EditText
    lateinit var edPassword:EditText
    lateinit var edEmail:EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_sign_up, container, false)
        // Inflate the layout for this fragment
        edUesr=view.findViewById(R.id.edUserSign)
        edPassword=view.findViewById(R.id.edPasswordSign)
        edEmail=view.findViewById(R.id.edEmailSign)
        return view
    }

    companion object {
        val instance by lazy {
            SignUpFragment()
        }
    }

}