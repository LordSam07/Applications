package com.lordsam.kalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var operationValue = "+"
    private var oldNumber = ""
    private var isOP = true

    fun buClick(view :View){

        if (isOP){
            Log.i("check","Here")
            textView.text = ""
            isOP = false
        }

        val buttonSelected = view as Button
        var buttonValue = textView.text.toString()

        when (buttonSelected.id){
            bu0.id -> {
                buttonValue += "0"
            }
            bu1.id -> {
                buttonValue += "1"
            }
            bu2.id -> {
                buttonValue += "2"
            }
            bu3.id -> {
                buttonValue += "3"
            }
            bu4.id -> {
                buttonValue += "4"
            }
            bu5.id -> {
                buttonValue += "5"
            }
            bu6.id -> {
                buttonValue += "6"
            }
            bu7.id -> {
                buttonValue += "7"
            }
            bu8.id -> {
                buttonValue += "8"
            }
            bu9.id -> {
                buttonValue += "9"
            }
            buDot.id -> {
                buttonValue += "."
            }
            buSign.id -> {
                buttonValue = "-$buttonValue"
            }
        }

        textView.text = buttonValue
    }

    fun buOPClick(view :View){

        val buttonSelected = view as Button
        when (buttonSelected.id) {
            buDiv.id -> {
                operationValue = "/"
                textView2.text = "/"
            }
            buMul.id -> {
                operationValue = "*"
                textView2.text = "*"
            }
            buMinus.id -> {
                operationValue = "-"
                textView2.text = "-"
            }
            buPlus.id -> {
                operationValue = "+"
                textView2.text = "+"
            }
        }
        oldNumber = textView.text.toString()
        Log.i("old", "old $oldNumber")
        isOP = true
    }

    fun buEQClick(view :View){
        val newNumber = textView.text.toString()
        Log.i("old", "new $newNumber")
        var finalValue :Double? = null

        when (operationValue){
            "+" -> {
                finalValue = oldNumber.toDouble() + newNumber.toDouble()
            }
            "-" -> {
                finalValue = oldNumber.toDouble() - newNumber.toDouble()
            }
            "*" -> {
                finalValue = oldNumber.toDouble() * newNumber.toDouble()
            }
            "/" -> {
                finalValue = oldNumber.toDouble() / newNumber.toDouble()
            }
        }
        textView.text = finalValue.toString()
        isOP = true

    }

    fun buClear(view :View){
        textView.text = ""
        isOP = true

    }

    fun buPercent(view :View){
        var percent = textView.text.toString().toDouble() / 100
        textView.text = percent.toString()
        isOP = true

    }
}
