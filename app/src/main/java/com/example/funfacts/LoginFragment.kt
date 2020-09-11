package com.example.funfacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class LoginFragment : Fragment() {
    lateinit var edUser :EditText
    lateinit var edPassword:EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_login, container, false)

        edUser=view.findViewById(R.id.edUserLogin)
        edPassword=view.findViewById(R.id.edPasswordLogin)

        return view
    }

    companion object {
        val instance by lazy {
            LoginFragment()
        }
    }
}