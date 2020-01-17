package com.lordsam.messageshare.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lordsam.messageshare.Extensions.Constants
import com.lordsam.messageshare.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle :Bundle? = intent.extras
        val msg = bundle!!.getString(Constants.USER_MSG_KEY)
        textView.text = msg
    }
}