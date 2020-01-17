package com.lordsam.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            val msg: String = editText.text.toString()

            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("user_msg", msg)

            startActivity(intent)
        }

            button2.setOnClickListener {
                val msg: String = editText.text.toString()

                val intent = Intent()

                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT , msg)
                intent.type = "text/plain"

                startActivity(Intent.createChooser(intent ," send"))
            }

        button3.setOnClickListener {
            val intent = Intent(this, HobbiesActivity::class.java)
        }
    }
}
