package com.example.cse438.cse438_assignment2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cse438.cse438_assignment2.R
import com.example.cse438.cse438_assignment2.fragments.HighestFragment
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity() {
    var highestValue: Int = 0

    public lateinit var cancelButton: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        //setView
        cancelButton = cancel_button


        //set initial fragment
        val fragment = HighestFragment()
        var bundle = Bundle()
        bundle.putInt("highestValue", highestValue)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }


    override fun onStart() {
        super.onStart()

        cancelButton.setOnClickListener {
            finish()
        }
    }
}

