package com.example.cse438.cse438_assignment2.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.cse438.cse438_assignment2.R
import kotlinx.android.synthetic.main.fragment_highest.*
import kotlinx.android.synthetic.main.fragment_lowest.*

class LowestFragment : Fragment() {

    public var numOfDice: Int = 0
    public var maxDiceValue: Int = 0
    var rollhistory = arrayListOf<Int>()
    public var rollTimes: Int = 0

    public lateinit var averageButton: Button
    public lateinit var highestButton: Button
    public lateinit var lowestValueDisplayed: TextView
    public lateinit var probabilityButton: TextView

    var highestValue: Int = 0
    var lowestValue: Int = 0
    var averageValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = activity!!.intent
        numOfDice = intent!!.getIntExtra("numOfDice", 0)
        maxDiceValue = intent!!.getIntExtra("maxDiceValue", 0)
        rollhistory = intent!!.getIntegerArrayListExtra("rollHistory")
        rollTimes = intent!!.getIntExtra("rollTimes", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lowest, container, false)
    }

    override fun onStart() {
        super.onStart()

        //calculate highest roll number
        var min: Int = rollhistory[0]
        for (i in 0 until rollTimes) {
            if (rollhistory[i] < min) {
                min = rollhistory[i]
            }
        }

        //set views
        averageButton = average_Button_InLowest
        highestButton = highest_Button_InLowest
        probabilityButton = prob_Button_InLowest
        lowestValueDisplayed = lowestValue_actual
        lowestValueDisplayed.text = min.toString()



        average_Button_InLowest.setOnClickListener {
            val fragment = AverageFragment()
            var bundle = Bundle()
            bundle.putInt("averageValue", averageValue)
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
        highest_Button_InLowest.setOnClickListener {
            val fragment = HighestFragment()
            var bundle = Bundle()
            bundle.putInt("averageValue", highestValue)
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
        prob_Button_InLowest.setOnClickListener {
            val fragment = ProbabilityFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }


    }
}
