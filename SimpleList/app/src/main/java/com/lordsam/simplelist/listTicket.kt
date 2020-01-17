package com.lordsam.simplelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list_ticket.*

class listTicket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_ticket)

        val bundle :Bundle? = intent.extras
        val name = bundle!!.getString("name")

        textView.text = name
    }
}
