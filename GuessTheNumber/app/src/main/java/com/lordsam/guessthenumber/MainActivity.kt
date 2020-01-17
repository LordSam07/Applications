package com.lordsam.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        guessButton.setOnClickListener {

            var rangeValue = rangeBox.text.toString().toInt()
            var guessValue = guessBox.text.toString().toInt()
            var randomValue = Random().nextInt(rangeValue)

            if (guessValue < randomValue){
                Toast.makeText(this,"Too Low!",Toast.LENGTH_SHORT).show()
            }
            else if (guessValue > randomValue){
                Toast.makeText(this,"Too High!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Theif found: $randomValue",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
