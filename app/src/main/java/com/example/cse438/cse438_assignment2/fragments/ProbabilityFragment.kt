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
import kotlinx.android.synthetic.main.fragment_probability.*


class ProbabilityFragment : Fragment() {
    var probLow: Int = 0
    var probAverage: Int = 0
    var probHigh: Int = 0
    var averageRange: Int = 0
    var lowRange: Int = 0
    var rollTimes: Int = 0
    var highRange: Int = 0

    public lateinit var highestButton: Button
    public lateinit var lowestButton: Button
    public lateinit var averageButton: Button

    public lateinit var averageValueDisplayed: TextView
    public lateinit var highValueDisplayed: TextView
    public lateinit var lowValueDisplayed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get values
        val intent = activity!!.intent
        highRange = intent!!.getIntExtra("highRange", 0)
        averageRange = intent!!.getIntExtra("averageRange", 0)
        lowRange = intent!!.getIntExtra("lowRange", 0)
        rollTimes = intent!!.getIntExtra("rollTimes", 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_probability, container, false)
    }

    override fun onStart() {
        super.onStart()
        //calculate probability of getting high/average/low roll, I am setting the probability here to
        //the integer place for simplicity of view
        probAverage = (averageRange.toDouble() / rollTimes.toDouble() * 100).toInt()
        probHigh = ((highRange.toDouble() / rollTimes.toDouble()) * 100).toInt()
        probLow = ((lowRange.toDouble() / rollTimes.toDouble()) * 100).toInt()

        //set views
        highestButton = highest_Button_InProb
        lowestButton = lowest_Button_InProb
        averageButton = average_Button_InProb
        averageValueDisplayed = averageProb
        highValueDisplayed = highProb
        lowValueDisplayed = lowProb
        averageValueDisplayed.text = probAverage.toString() + "%"
        highValueDisplayed.text = probHigh.toString() + "%"
        lowValueDisplayed.text = probLow.toString() + "%"

        //set listeners
        highest_Button_InProb.setOnClickListener {
            val fragment = HighestFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
        average_Button_InProb.setOnClickListener {
            val fragment = AverageFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
        lowest_Button_InProb.setOnClickListener {
            val fragment = LowestFragment()
            var bundle = Bundle()
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }
}

