package com.lordsam.easynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }

    fun buFinish(view: View){
        finish()
    }
}
