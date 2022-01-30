package com.example.hw9

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import java.security.acl.Group

class InfoFragment : Fragment(R.layout.info_fragment_layout) {


    private lateinit var tvFullnameResult: TextView
    private lateinit var tvUsernameResult: TextView
    private lateinit var tvPasswordResult: TextView
    private lateinit var tvUEmailResult: TextView
    private lateinit var tvGenderResult: TextView
    lateinit var infoGroup: androidx.constraintlayout.widget.Group
    private lateinit var btnSave: Button

    private lateinit var sp :SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

       view.findViewById<Button>(R.id.saveInfo).setOnClickListener{

           if (saveData()) Toast.makeText(context,"SAVED!",Toast.LENGTH_SHORT).show()
           parentFragmentManager.commit {
               replace<DataFragment1>(R.id.data_fragment_con)
               setReorderingAllowed(true)
           }

       }
    }

    private fun saveData() :Boolean {
        val sp :SharedPreferences = context!!.getSharedPreferences("User",MODE_PRIVATE)
        val editor = sp.edit().apply() {
            putString("fullname", tvFullnameResult.text.toString())
            putString("username", tvUsernameResult.text.toString())
            putString("email", tvUEmailResult.text.toString())
            putString("password", tvPasswordResult.text.toString())
            putString("gender", tvGenderResult.text.toString())
        }
        return editor.commit()
    }
    override fun onStart() {
        super.onStart()
        setValues()
    }

    private fun initViews() {

        view?.apply {
            infoGroup = findViewById(R.id.infoGroup)
            tvFullnameResult = findViewById(R.id.tvFullnameResult)
            tvUsernameResult = findViewById(R.id.tvUsernameResult)
            tvPasswordResult = findViewById(R.id.tvPasswordResult)
            tvUEmailResult = findViewById(R.id.tvUEmailResult)
            tvGenderResult = findViewById(R.id.tvGenderResult)
        }
    }
    private fun setValues() {

        tvFullnameResult.text = requireArguments().getString("fullname", "Full name")
        tvUsernameResult.text = requireArguments().getString("username", "Username")
        tvUEmailResult.text = requireArguments().getString("email", "Email")
        tvPasswordResult.text = requireArguments().getString("password", "Password")
        tvGenderResult.text = requireArguments().getString("gender", "Gender")

    }

}