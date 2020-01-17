package com.lordsam.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun action(view:View){

        var input = editText.text.toString()
        var output :Double

        if (input.toDouble() > 100){
            output = input.toDouble() + input.toDouble()*.10
            editText.setText(output.toString())
        }
        else{
            editText.setText(input)
        }



    }

}
