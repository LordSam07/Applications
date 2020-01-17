package com.lordsam.messageshare.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lordsam.messageshare.Extensions.Constants
import com.lordsam.messageshare.R
import com.lordsam.messageshare.Extensions.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0

        button.setOnClickListener {

            Log.i("MainActivity", "Button was clicked ${++count}")

            showToast(resources.getString(R.string.show_toast) + " $count")
        }

        button2.setOnClickListener {
            val str = editText.text.toString()
            showToast(str)

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(Constants.USER_MSG_KEY,str) //to second activity
            startActivity(intent)
        }

        //to share elsewhere
        button3.setOnClickListener {
            val msg = editText.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, msg)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Select Target :"))
        }

        buttonRecyclerView.setOnClickListener {

            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }
    }
}

