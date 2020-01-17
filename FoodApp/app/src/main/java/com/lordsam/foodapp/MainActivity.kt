package com.lordsam.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var adapter :FoodAdapter? = null
    var listOfFood = ArrayList<Food>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listOfFood.add(Food("Coffee", "Coffee is hot.", R.drawable.coffee_pot))
        listOfFood.add(Food("Espresso", "Espresso makes you high.", R.drawable.espresso))
        listOfFood.add(Food("French Fries", "Grench Fries are crispy.", R.drawable.french_fries))
        listOfFood.add(Food("Honey", "Honey is healthy.", R.drawable.honey))
        listOfFood.add(Food("Ice-Cream", "Ice-Cream is for life.", R.drawable.strawberry_ice_cream))
        listOfFood.add(Food("Sugar Cubes", "Sugar is sweet.", R.drawable.sugar_cubes))

        adapter = FoodAdapter(this, listOfFood)
        foodGrid.adapter = adapter
    }


    class FoodAdapter :BaseAdapter{

        var listOfFood = ArrayList<Food>()
        var context :Context? = null

        constructor(context :Context, listOfFood :ArrayList<Food>) :super(){
            this.context = context
            this.listOfFood = listOfFood
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
          val food = listOfFood[position]
            val  inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val foodView = inflater.inflate(R.layout.food_ticket, null)
            foodView.foodImage.setImageResource(food.image!!)
            foodView.foodText.text = food.name!!
            foodView.foodImage.setOnClickListener {

                val intent = Intent(context, FoodDetails::class.java)
                intent.putExtra("name", food.name!!)
                intent.putExtra("description", food.description!!)
                intent.putExtra("image", food.image!!)
                context!!.startActivity(intent)
            }
            return foodView
        }

        override fun getItem(position: Int): Any {
            return listOfFood[position]
        }

        override fun getItemId(position: Int): Long {
           return position.toLong()
        }

        override fun getCount(): Int {
            return listOfFood.size
        }

    }
}
