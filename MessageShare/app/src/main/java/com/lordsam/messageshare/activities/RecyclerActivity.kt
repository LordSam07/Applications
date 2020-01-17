package com.lordsam.messageshare.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lordsam.messageshare.adapters.HobbiesAdapter
import com.lordsam.messageshare.R
import com.lordsam.messageshare.Extensions.Supplier
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        linkAdapterAndLayoutManager()
    }

    private fun linkAdapterAndLayoutManager() {

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view.layoutManager = layoutManager

        val adapter = HobbiesAdapter(
            this,
            Supplier.hobbies
        )
        recycler_view.adapter = adapter    }
}