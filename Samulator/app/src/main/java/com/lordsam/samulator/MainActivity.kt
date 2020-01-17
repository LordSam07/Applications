package com.lordsam.samulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun clickEvent(view :View){

        if (isnewop == true){
            editText.setText("")
            isnewop = false
        }
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
        }

        editText.setText(buClick)
}

    var op ='*'
    var oldnumbers = ""
    var isnewop = true
    fun buOp(view:View){

        val buSelect = view as Button
        when(buSelect.id){
            bumul.id -> {op = '*'}
            budiv.id -> {op = '/'}
            busub.id -> {op = '-'}
            buAdd.id -> {op = '+'}
        }

        oldnumbers=editText.text.toString()
        isnewop=true
    }


    fun equal (view:View){
        val newNumber = editText.text.toString()
        var finalNumber:Double? = null
        when(op){
            '*' -> {
                finalNumber = oldnumbers.toDouble() * newNumber.toDouble()
            }
            '/' -> {
                finalNumber = oldnumbers.toDouble() / newNumber.toDouble()
            }
            '-' -> {
                finalNumber = oldnumbers.toDouble() - newNumber.toDouble()
            }
            '+' -> {
                finalNumber = oldnumbers.toDouble() + newNumber.toDouble()
            }
        }

        editText.setText(finalNumber.toString())
        isnewop = true
    }


    fun bupercent(view: View){
        val number = editText.text.toString().toDouble()/100

        editText.setText(number.toString())
        isnewop=true
    }

    fun buclean (view: View){

        editText.setText("")
        isnewop=true
    }


}
