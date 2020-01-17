package com.lordsam.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_food_details.*

class FoodDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        val bundle :Bundle?= intent.extras

        FoodImageDetails.setImageResource(bundle!!.getInt("image"))
        FoodTextDetails.text = bundle!!.getString("name")
        FoodDescriptionDetails.text = bundle!!.getString("description")
    }
}
