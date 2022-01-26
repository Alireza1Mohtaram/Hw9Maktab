package com.example.hw9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentOnAttachListener
import androidx.fragment.app.add
import androidx.fragment.app.commit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.commit {

            add<DataFragment1>(R.id.data_fragment_con)
            setReorderingAllowed(true)
        }


//        supportFragmentManager.addFragmentOnAttachListener(FragmentOnAttachListener
//        { fragmentManager, fragment ->
//
//            fragmentManager.
//
//
//        }


        //)


    }



    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStackImmediate()

    }
}