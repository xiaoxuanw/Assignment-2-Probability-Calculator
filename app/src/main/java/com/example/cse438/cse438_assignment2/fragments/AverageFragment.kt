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
import kotlinx.android.synthetic.main.fragment_average.*
import kotlinx.android.synthetic.main.fragment_lowest.*

class AverageFragment : Fragment() {

    public var numOfDice: Int = 0
    public var maxDiceValue: Int = 0
    var rollhistory = arrayListOf<Int>()
    public var rollTimes: Int = 0
    var highestValue: Int = 0
    var lowestValue: Int = 0
    public lateinit var highestButton: Button
    public lateinit var lowestButton: Button
    public lateinit var probabilityButton: TextView
    public lateinit var averageValueDisplayed: TextView

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
        return inflater.inflate(R.layout.fragment_average, container, false)
    }

    override fun onStart() {
        super.onStart()

        //calculate average roll number
        var sum = 0
        for (i in 0 until rollTimes) {
            sum += rollhistory[i]
        }
        var average = sum / rollTimes


        //set views
        highestButton = highest_Button_InAverage
        lowestButton = lowest_Button_InAverage
        probabilityButton = prob_Button_InAverage
        averageValueDisplayed = averageValue_actual
        averageValueDisplayed.text = average.toString()

        //set listener
        highest_Button_InAverage.setOnClickListener {
            val fragment = HighestFragment()
            var bundle = Bundle()
            bundle.putInt("highestValue", highestValue)
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }

        lowest_Button_InAverage.setOnClickListener {
            val fragment = LowestFragment()
            var bundle = Bundle()
            bundle.putInt("lowestValue", lowestValue)
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
        prob_Button_InAverage.setOnClickListener {
            val fragment = ProbabilityFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }
}



