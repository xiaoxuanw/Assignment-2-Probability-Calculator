package com.example.cse438.cse438_assignment2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.example.cse438.cse438_assignment2.R

class MainActivity : AppCompatActivity() {
    public var numOfDice: Int = 0
    public var maxDiceValue: Int = 0
    var diceList = arrayListOf<Int>()
    var rollHistory = arrayListOf<Int>()
    var returnMain: Boolean = false

    public lateinit var rollButton: Button
    public lateinit var clearButton: Button
    var lowRange: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the data from previous activity if exists
        if (returnMain) {
            val intent = intent
            numOfDice = intent!!.getIntExtra("numOfDice", 0)
            maxDiceValue = intent.getIntExtra("maxDiceValue", 0)
            rollHistory = intent.getIntegerArrayListExtra("rollHistory")
            diceList = intent.getIntegerArrayListExtra("diceList")
            lowRange = intent.getIntExtra("lowRange", 0)
        }
    }

    override fun onStart() {
        super.onStart()
        //set view
        rollButton = rollButton_display
        clearButton = clear_button_display

        //set roll button listener
        rollButton.setOnClickListener {
            val addDiceNum = NumOfDiceText.text.toString()
            val addMaxDiceNum = MaxDiceValueText.text.toString()


            // Error message if no value entered
            if (addDiceNum == "" || addMaxDiceNum == "") {
                val myToast = Toast.makeText(this, "please enter valid values", Toast.LENGTH_SHORT)
                myToast.show()
            } else {
                numOfDice = addDiceNum.toInt()
                maxDiceValue = addMaxDiceNum.toInt()
                // error message if value entered are zero or a negative number
                if (numOfDice <= 0 || maxDiceValue <= 0) {
                    val myToast =
                        Toast.makeText(this, "please enter valid values", Toast.LENGTH_SHORT)
                    myToast.show()
                } else {
                    val intent = Intent(this, RollActivity::class.java)
                    intent.putExtra("numOfDice", numOfDice)
                    intent.putExtra("maxDiceValue", maxDiceValue)
                    intent.putExtra("rollHistory", rollHistory)
                    intent.putExtra("diceList", diceList)
                    startActivity(intent)
                }
            }
        }

        //set clear button listener
        clearButton.setOnClickListener {
            numOfDice = 0
            maxDiceValue = 0
            rollHistory.clear()
            diceList.clear()
            lowRange = 0
            val myToast = Toast.makeText(this, "Values are cleared", Toast.LENGTH_SHORT)
            myToast.show()
        }


    }
}

