package com.lordsam.zoo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter :AnimalsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listOfAnimals.add(
            Animal("Baboon",
            "Baboon is a naughty monkey",
            R.drawable.baboon, true))

        listOfAnimals.add(Animal("Bull Dog",
            "Bull dog is  charming",
            R.drawable.bulldog, false))

        listOfAnimals.add(Animal("Panda",
            "Panda is lazy af",
            R.drawable.panda, false))

        listOfAnimals.add(Animal("Swallow Bird",
            "Swallow bird is so  cute",
            R.drawable.swallow_bird, false))

        listOfAnimals.add(Animal("White Tiger",
            "Tiger is very strong",
            R.drawable.white_tiger, true))

        listOfAnimals.add(Animal("Zebra",
            "Zebra has black and white stripes",
            R.drawable.zebra, false))

        adapter = AnimalsAdapter(this, listOfAnimals)
        animalList.adapter = adapter
    }

    //to delete any item
    fun delete(index :Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    //to add on lick
    fun add(index :Int){
        listOfAnimals.add(listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }


    class AnimalsAdapter :BaseAdapter{

        var listOfAnimals = ArrayList<Animal>()
        var context :Context? = null

        constructor(context : Context, listOfAnimals :ArrayList<Animal>) :super(){
            this.listOfAnimals = listOfAnimals
            this.context = context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]

            if (animal.isKiller == false) {
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.baboonText.text = animal.name!!
                myView.baboonInfo.text = animal.description!!
                myView.baboonView.setImageResource(animal.image!!)
                myView.baboonView.setOnClickListener {
                    //delete(position)
                    val intent = Intent(context, Animal_Info::class.java)
                    intent.putExtra("name", animal.name!!)
                    intent.putExtra("description", animal.description!!)
                    intent.putExtra("image", animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }else{
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
                myView.baboonText.text = animal.name!!
                myView.baboonInfo.text = animal.description!!
                myView.baboonView.setImageResource(animal.image!!)
                myView.baboonView.setOnClickListener {
                    //delete(position)
                    val intent = Intent(context, Animal_Info::class.java)
                    intent.putExtra("name", animal.name!!)
                    intent.putExtra("description", animal.description!!)
                    intent.putExtra("image", animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }
        }

        override fun getItem(position: Int): Any {
           return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
