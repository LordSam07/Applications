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

    fun clickEvent(view : View){

        val buSelect = view as Button
        var buClick:String=editText.text.toString()

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

        editText.setText(buClick)
    }

    fun equal(){
        val obj = InfixEvaluation()
        val text = editText.text.toString()
        editText.text = obj.evaluate(text).toString()
    }


    fun bupercent(){
        val number = editText.text.toString().toDouble()/100

        editText.setText(number.toString())
    }

    fun buclean (){

        editText.setText("")
    }

}
