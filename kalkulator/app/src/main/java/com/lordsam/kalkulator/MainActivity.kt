package com.lordsam.kalkulator

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

        buequal.setOnClickListener {
            equal()
        }

        buper.setOnClickListener {
            bupercent()
        }

        buclean.setOnClickListener {
            buclean()
        }
    }

    var buClick = ""
    var isOpEnabled = true

    fun clickOperator(view :View){

        val ob = InfixEvaluation()
        Log.i("opselect","clicked $isOpEnabled")
        val opSelect = view as Button
        if (isOpEnabled) {
            when (opSelect.id) {
                bumul.id -> {
                    buClick += "*"
                }
                budiv.id -> {
                    buClick += "/"
                }
                busub.id -> {
                    buClick += "-"
                }
                buAdd.id -> {
                    buClick += "+"
                }
            }
            editText.text = buClick
        }
        else if (!isOpEnabled && (ob.isOperator(buClick[buClick.length - 1]))){

            var buArr = buClick.toCharArray()
            when (opSelect.id) {
                bumul.id -> {
                    buArr[buClick.length - 1] = '*'
                }
                budiv.id -> {
                    buArr[buClick.length - 1] = '/'
                }
                busub.id -> {
                    buArr[buClick.length - 1] = '-'
                }
                buAdd.id -> {
                    buArr[buClick.length - 1] = '+'
                }
            }
            for (i in buArr.indices)
                buClick += buArr[i]
            editText.text=buClick
            }
        isOpEnabled = false
    }

    fun clickEvent(view : View){

        buClick=editText.text.toString()

        val buSelect = view as Button

        when(buSelect.id){
            bu0.id->{
                buClick +="0"
            }
            bu1.id->{
                buClick +="1"
            }
            bu2.id->{
                buClick +="2"
            }
            bu3.id->{
                buClick +="3"
            }
            bu4.id->{
                buClick +="4"
            }
            bu5.id->{
                buClick +="5"
            }
            bu6.id->{
                buClick +="6"
            }
            bu7.id->{
                buClick +="7"
            }
            bu8.id->{
                buClick +="8"
            }
            bu9.id->{
                buClick +="9"
            }
            buDot.id->{
                buClick +="."
            }
            buplusminus.id->{
                buClick ="-"+buClick
            }
        }
        isOpEnabled = true
        Log.i("openable", isOpEnabled.toString())
        editText.text = buClick
    }

    fun equal(){
        val obj = InfixEvaluation()
        val text = editText.text.toString()
        editText.text = obj.evaluate(text).toString()
        isOpEnabled = true
    }


    fun bupercent(){
        val number = editText.text.toString().toDouble()/100
        isOpEnabled = true
        editText.setText(number.toString())
    }

    fun buclean (){
        editText.setText("")
    }

}
