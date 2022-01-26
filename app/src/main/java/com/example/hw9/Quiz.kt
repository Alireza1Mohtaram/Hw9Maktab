package com.example.hw9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hw9.databinding.ActivityQuizBinding

class Quiz : AppCompatActivity() {


    private var q1 = Pair<String, Boolean>(
        "1.Vitamin C is also known by the chemical name of Ascorbic Acid",
        true
    )
    private var q2 =
        Pair("2.The Pan American Highway in the U.S.A. is the world’s longest highway", true)
    private var q3 = Pair("3.Transylvania is a historical region located in central Romania", true)
    private var q4 = Pair("4.Glass is manufactured mainly from processed sand", true)
    private var q5 = Pair("5.Real Madrid football club’s biggest rival is Atlético Madrid", false)
    private var q6 = Pair(
        "6.There is no way of telling boiled eggs from raw eggs until you break them open",
        false
    )
    private var q7 =
        Pair("7.Dan Brown’s novel ‘Inferno’ was inspired by Dante Alighieri’s poem ‘Inferno’", true)
    private var q8 = Pair(
        "8.Manchester United football club has never won the UEFA Champions League trophy",
        false
    )
    private var q9 = Pair("9.Rio de Janeiro is the capital city of Brazil", false)
    private var q10 =
        Pair("10.A person’s fingernails and hair continue to grow after they die", false)
    private var qeustions = mutableListOf(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10)
    private var qeustionsCheat = Array<Boolean>(10) { false }

    lateinit var luncher: ActivityResultLauncher<Intent>

    lateinit var binding: ActivityQuizBinding

    companion object {
        var conterState = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLuncher()
        firstinit()

        binding.btnTrue.setOnClickListener {
            getCurrentAnswer(true)
        }
        binding.btnFalse.setOnClickListener {
            getCurrentAnswer(false)
        }
        binding.btnCheat.setOnClickListener {
            var i = Intent(this@Quiz, CheatActivity::class.java)
            i.putExtra("answer", qeustions[conterState].second)
            luncher.launch(i)
        }
        binding.btnNext.setOnClickListener {
            nextQes()
        }
        binding.btnPrev.setOnClickListener {
            pervQes()
        }

    }

    private fun getLuncher() {
        luncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            qeustionsCheat[conterState] = it.data?.getBooleanExtra("cheat", false) ?: false

        }

    }


    private fun getCallback(): ActivityResultCallback<Boolean> {
        return ActivityResultCallback {

        }
    }

    private fun notify(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun nextQes() {
        binding.btnPrev.isEnabled = true
        if (conterState == 8) binding.btnNext.isEnabled = false

            conterState++
            binding.qesBox.text = qeustions[conterState].first


    }

    private fun pervQes() {
        binding.btnNext.isEnabled = true

            if (conterState  == 1) binding.btnPrev.isEnabled = false
            conterState--
            binding.qesBox.text = qeustions[conterState].first

    }

    private fun getCurrentAnswer(userAnswer: Boolean) {

        if (qeustions[conterState].second == userAnswer) {
            if (qeustionsCheat[conterState]) {
                notify("Cheat is Wrong")
                return
            } else {
                notify("Correct!")
                return
            }
        }
        notify("InCorrect!")
        return
    }

    private fun firstinit() {
        binding.qesBox.text = qeustions[conterState].first
        binding.btnPrev.isEnabled = false
    }

}