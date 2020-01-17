package com.lordsam.timepass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun action(view: View){

        var input = editText2.text.toString()

        if (input.toInt() == 3){
            Toast.makeText(this, "Chal baap ko mat sikha",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "ja na chutiye",Toast.LENGTH_SHORT).show()
        }

    }
}
