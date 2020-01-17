package com.lordsam.messageshare.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lordsam.messageshare.Extensions.Hobby
import com.lordsam.messageshare.R
import com.lordsam.messageshare.Extensions.showToast
import kotlinx.android.synthetic.main.item_list.view.*

class HobbiesAdapter(private val context :Context, private val hobbies :List<Hobby>) :RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView){

        var currentHobby : Hobby? = null
        var currentPosition = 0

        init {
            itemView.setOnClickListener {
                context.showToast(currentHobby!!.title + " Clicked!")
            }

            itemView.imgShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, "My pet is " + currentHobby!!.title + " at " + currentPosition)
                intent.type = "text/plain"
                context.startActivity(Intent.createChooser(intent, "Select Target"))

            }
        }

        fun setData(hobby : Hobby?, pos :Int){
            hobby?.let {
                itemView.txvTitle.text = hobby.title

                this.currentHobby = hobby
                this.currentPosition = pos
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hobbies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hobby = hobbies[position]
        holder.setData(hobby, position)
    }

}