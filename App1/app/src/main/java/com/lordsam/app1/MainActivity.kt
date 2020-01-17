package com.lordsam.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        button1.setOnClickListener {
            Log.i("MainActivity", "You Clicked Me!");
            Toast.makeText(this,"You Clicked Me!",Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {

            val msg:String = editText1.text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            intent.putExtra("user_message",msg)

        }
    }
}
