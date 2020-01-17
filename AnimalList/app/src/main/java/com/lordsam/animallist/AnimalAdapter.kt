package com.lordsam.animallist

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class AnimalAdapter(val context : Context,val animals :List<Animal>) :RecyclerView.Adapter<AnimalAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val animal = animals[position]
        holder.setData(animal, position)
     }


    inner class MyViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView){

        var currentAnimal :Animal? = null
        var currentPosition = 0

        init {
            itemView.setOnClickListener {
                Toast.makeText(context, currentAnimal!!.name + " Clicked!", Toast.LENGTH_SHORT).show()
            }

            itemView.imgShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, "My pet is " + currentAnimal!!.name)
                intent.type = "text/plain"
                context.startActivity(Intent.createChooser(intent, "Select Target"))

            }
        }

        fun setData(animal :Animal?, pos: Int){
            itemView.txvTitle.text = animal!!.name

            this.currentAnimal = animal
            this.currentPosition = pos
        }
    }
}