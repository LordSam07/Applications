package com.lordsam.minigames

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chat_bot.*

class ChatBot :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
    }

    fun talk(view :View){
        val chat = botText.text.toString()

        when (chat){
            "abba harmonium bajatey they" -> replyText.setText("Nhi Abba Harmonium Khatey They!")

            "tamiz se" -> replyText.text = "Maaf Krna Galti Se Idhar Udhar Nikal Jata Hu."

            else -> replyText.text = "Sorry, I'm Noob!"
        }
    }

}