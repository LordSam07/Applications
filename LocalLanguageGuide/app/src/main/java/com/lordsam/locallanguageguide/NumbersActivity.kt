package com.lordsam.locallanguageguide


import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)

        var numberArray = ArrayList<Word>()
        numberArray.add(Word("One", "One"))


        //adding child view without using XML
        /*var rootView = findViewById<LinearLayout>(R.id.rootView)
        var wordView = TextView(this)
        wordView.text = numberArray[0]
        rootView.addView(wordView)*/

        //predefined simple_list_item_1
        /*val itemsAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, numberArray)*/

        val itemsAdapter: WordAdapter =
            WordAdapter(this, numberArray)

        val listView: ListView = findViewById<View>(R.id.list) as ListView
        listView.adapter = itemsAdapter

    }
}
