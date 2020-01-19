package com.lordsam.minigames

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_noob_math.*
import kotlin.random.Random

class NoobMath :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noob_math)

        var display: String

        buttonOk.setOnClickListener {
            display = askQuestion()
            textDisplay.text = display
        }

        buttonReply.setOnClickListener {
            check()
        }
    }

   private var randomPicker = Random.nextInt(0, 2)

    private fun askQuestion(): String {

        var question = ""

        when (randomPicker){

            0 -> question = "What is the symbol of addition?"
            1 -> question = "If you had 4 eggs and you boiled 2, how many do you have now?"


        }
        return question
    }

    private fun toaster(comment :String){
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show()
    }

    private fun check(){
        val answer = editTextAnswer.text.toString()
        var value :Int? = null

        when(answer){
            "+" -> value = 0
            "4" -> value = 1
        }


        if (randomPicker == value){
            toaster("GG Noob!")
        }
        else{
            toaster("What A Noob!")
        }
    }
}
