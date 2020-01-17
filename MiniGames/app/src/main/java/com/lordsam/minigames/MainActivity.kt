package com.lordsam.minigames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonChatBot.setOnClickListener {
            val intent = Intent(this, ChatBot::class.java)
            startActivity(intent)
        }

        buttonNoobMath.setOnClickListener {
            val intent = Intent(this, NoobMath::class.java)
            startActivity(intent)
        }

        buttonComingSoon.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }
    }
}
