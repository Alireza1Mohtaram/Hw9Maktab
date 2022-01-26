package com.example.hw9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hw9.databinding.ActivityCheatBinding
import com.example.hw9.databinding.ActivityQuizBinding

class CheatActivity : AppCompatActivity() {

    lateinit var binding : ActivityCheatBinding

    var isCheate = false
    var answerIntent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheatBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.btnChaetAnswer.setOnClickListener {


            var answer = intent.getBooleanExtra("answer",false)
            binding.tvAnswer.text = answer.toString()
            isCheate = true
        }


    }

    override fun onBackPressed() {
        answerIntent.putExtra("cheat",isCheate)
        setResult(RESULT_OK , answerIntent)
        super.onBackPressed()
    }
}