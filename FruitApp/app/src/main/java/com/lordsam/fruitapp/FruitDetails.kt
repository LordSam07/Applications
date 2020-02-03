package com.lordsam.fruitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fruit_details.*

class FruitDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_details)

        var bundle :Bundle = intent.extras!!
        var name = bundle.getString("name")
        var info = bundle.getString("info")
        var img  = bundle.getInt("img")

        imageView2.setImageResource(img)
        textViewA.text = name
        textView2A.text = info
    }
}
