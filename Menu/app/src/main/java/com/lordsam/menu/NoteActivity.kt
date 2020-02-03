package com.lordsam.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }

    fun onAdd(view: View){
        finish()
    }
}
