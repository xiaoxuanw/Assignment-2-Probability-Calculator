package com.example.cse438.cse438_assignment2.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cse438.cse438_assignment2.R
import kotlinx.android.synthetic.main.activity_roll.*
import java.util.*

class RollActivity : AppCompatActivity() {
    public var numOfDice: Int = 0
    public var maxDiceValue: Int = 0
    var totalDiceValue: Int = 0
    var rollTimes: Int = 0
    var diceList = arrayListOf<Int>()
    var rollHistory = arrayListOf<Int>()
    var returnMain: Boolean = false

    var highRange: Int = 0
    var averageRange: Int = 0
    var lowRange: Int = 0


    public lateinit var seeResultButton: Button
    public lateinit var rerollButton: Button
    public lateinit var homeButton: Button
    public lateinit var valueDisplayed: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll)

        //get values
        val intent = intent
        numOfDice = intent!!.getIntExtra("numOfDice", 0)
        maxDiceValue = intent!!.getIntExtra("maxDiceValue", 0)

    }

    override fun onStart() {
        super.onStart()
        //set views
        seeResultButton = See_Results_Button
        rerollButton = Reroll_Button
        homeButton = Home_Button
        valueDisplayed = Value


        //calculate Total dice value
        var rollValue = arrayListOf<Int>()
        for (i in 0..numOfDice) {
            rollValue.add(Random().nextInt(maxDiceValue) + 1)
        }

        totalDiceValue = rollValue.sum()
        rollHistory.add(totalDiceValue)
        rollTimes++

        //set total dice value view to different colors

        val textView: TextView = findViewById(R.id.Value)

        //set low range values to red
        if (totalDiceValue < maxDiceValue * numOfDice / 2) {
            valueDisplayed.text = totalDiceValue.toString()
            textView.setTextColor(Color.RED)
            lowRange++
        }
        //set average to black
        else if (totalDiceValue == maxDiceValue * numOfDice / 2) {
            valueDisplayed.text = totalDiceValue.toString()
            textView.setTextColor(Color.BLACK)
            averageRange++
        }
        //set high range values to green
        else {
            valueDisplayed.text = totalDiceValue.toString()
            textView.setTextColor(Color.GREEN)
            highRange++
        }

        //set listener to see results
        seeResultButton.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)
            intent.putExtra("numOfDice", numOfDice)
            intent.putExtra("maxDiceValue", maxDiceValue)
            intent.putIntegerArrayListExtra("rollHistory", rollHistory)
            intent.putExtra("rollTimes", rollTimes)
            intent.putExtra("lowRange", lowRange)
            intent.putExtra("averageRange", averageRange)
            intent.putExtra("highRange", highRange)
            startActivity(intent)
        }

        //set listener to reroll
        rerollButton.setOnClickListener {
            rollTimes++
            var rollValue = arrayListOf<Int>()
            for (i in 0 until numOfDice) {
                rollValue.add(Random().nextInt(maxDiceValue) + 1)
            }
            totalDiceValue = rollValue.sum()
            rollHistory.add(totalDiceValue)

            val textView: TextView = findViewById(R.id.Value)

            //set total dice value view to different colors
            if (totalDiceValue < maxDiceValue * numOfDice / 2) {
                valueDisplayed.text = totalDiceValue.toString()
                textView.setTextColor(Color.RED)
                lowRange++
            } else if (totalDiceValue == maxDiceValue * numOfDice / 2) {
                valueDisplayed.text = totalDiceValue.toString()
                textView.setTextColor(Color.BLACK)
                averageRange++
            } else {
                valueDisplayed.text = totalDiceValue.toString()
                textView.setTextColor(Color.GREEN)
                highRange++
            }
        }
        //set listener to home
        homeButton.setOnClickListener {
            returnMain = true
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("diceList", diceList)
            intent.putExtra("rollHistory", rollHistory)
            intent.putExtra("numOfDice", numOfDice)
            intent.putExtra("maxDiceValue", maxDiceValue)
            intent.putExtra("lowRange", lowRange)
            intent.putExtra("averageRange", averageRange)
            intent.putExtra("highRange", highRange)
            startActivity(intent)
        }
    }
}