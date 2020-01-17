package com.lordsam.test

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val bundle: Bundle? = intent.extras
        val msg = bundle?.getString("user_msg")

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

        textView.text = msg
    }

}
