package com.lordsam.zoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animal__info.*

class Animal_Info : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal__info)

        val bundle :Bundle? = intent.extras
        val name = bundle!!.getString("name")
        val description = bundle!!.getString("description")
        val image = bundle!!.getInt("image")

        imageView.setImageResource(image)
        nameTextView.text = name
        infoTextView.text = description
    }
}
