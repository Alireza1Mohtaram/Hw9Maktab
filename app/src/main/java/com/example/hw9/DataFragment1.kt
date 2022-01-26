package com.example.hw9

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.hw9.databinding.DataFragmentLayoutBinding


class DataFragment1 : Fragment(R.layout.data_fragment_layout) {


    private lateinit var editTextPersonFullName: EditText
    lateinit var editTextUsername: EditText
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextRetypePassword: EditText

    lateinit var radioBtnFemale: RadioButton
    lateinit var radioBtnMale: RadioButton

    lateinit var btnRegister: Button

    lateinit var binding:DataFragmentLayoutBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        btnRegister.setOnClickListener {
            if (validateInputs()) {
                parentFragmentManager.commit {
                    replace<InfoFragment>(R.id.data_fragment_con, args = carryData())
                    addToBackStack("Info")
                    setReorderingAllowed(true)
                }
            }
        }
    }

    private fun carryData(): Bundle {

        return Bundle().apply {
            putString("fullname", editTextPersonFullName.text.toString())
            putString("username", editTextUsername.text.toString())
            putString("email", editTextEmail.text.toString())
            putString("password", editTextPassword.text.toString())
            putString("gender", getChecked())
        }
    }

    //  find radio checked
    private fun getChecked(): String {
        if (radioBtnMale.isChecked) return radioBtnMale.text.toString()
        return radioBtnFemale.text.toString()

    }

    //check EditText is blank or not
    private fun EditText.validEditText(): Boolean {
        if (this.text.isBlank()) {
            this.error = "Empty"
            println("${this.text} = true ")
            return false
        }
        return true
    }

    //validate one by one editTexts
    private fun validateInputs(): Boolean {
        var r: Boolean = true
        r = editTextPersonFullName.validEditText() &&
                editTextUsername.validEditText() &&
                editTextEmail.validEditText() &&
                editTextPassword.validEditText() &&
                editTextRetypePassword.validEditText()

        if (editTextRetypePassword.text.toString() != editTextPassword.text.toString()) {
            notify("Password and Re-typed Password not same")
            r = false
        }
        return r
    }

    private fun notify(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {

        binding = view?.let { DataFragmentLayoutBinding.bind(it) }!!
        // init Edittext

            editTextPersonFullName = binding.editTextPersonFullName
            editTextUsername = binding.editTextUsername
            editTextEmail = binding.editTextEmail
            editTextPassword = binding.editTextPassword
            editTextRetypePassword =binding.editTextRetypePassword

            // init radios
            radioBtnFemale = binding.radioBtnFemale
            radioBtnMale = binding.radioBtnMale

            //init Buttons
            btnRegister = binding.btnRegister

    }
}