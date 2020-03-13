package com.lordsam.easynotes

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_note.*
import java.lang.Exception

class NoteActivity : AppCompatActivity() {

    private var id = 0

    override fun onStart() {
        super.onStart()
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        //title
        try {
            val bundle = intent.extras
            val actionBarTitle = bundle!!.getString("actionBar")
            if (actionBarTitle == "edit")
                title = "Edit Note"
        }catch (ex :Exception){}


        buttonAdd.setOnClickListener { buAdd() }

        try {
            val bundle = intent.extras
            id = bundle!!.getInt("ID", 0)
            if (id != 0) {
                editText2.setText(bundle.getString("Title"))
                editText.setText(bundle.getString("Info"))
            }
        } catch (ex: Exception) {}
    }

    private fun buAdd() {
        val dbManager = DatabaseManager(this)
        val values = ContentValues()

        values.put("Title", editText2.text.toString())
        values.put("Info", editText.text.toString())

        if (id == 0) {
            val id = dbManager.insert(values)
            if (id > 0) {
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Insertion Failed!", Toast.LENGTH_SHORT).show()

            }
        } else {
            val selectionArgs = arrayOf(id.toString())
            val id = dbManager.update(values, "ID = ?", selectionArgs)
            if (id > 0) {
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Insertion Failed!", Toast.LENGTH_SHORT).show()

            }
        }
        finish()
    }
}
