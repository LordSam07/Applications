package com.lordsam.weekdays

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class WeekDayAdapter(val context :Context,val days: List<WeekDay>) :RecyclerView.Adapter<WeekDayAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView){



        fun setData(day :WeekDay?, pos :Int){
            itemView.textView.text = day!!.day
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val day = days[position]
        holder.setData(day, position)
    }

}