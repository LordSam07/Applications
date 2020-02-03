package com.lordsam.jsonpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(context: Context,arrayListDetails:ArrayList<Model>) : BaseAdapter(){

    private val layoutInflater: LayoutInflater
    private val arrayListDetails:ArrayList<Model>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails=arrayListDetails
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.tvName.text = arrayListDetails.get(position).name
        listRowHolder.tvEmail.text = arrayListDetails.get(position).email
        listRowHolder.tvId.text = arrayListDetails.get(position).id
        return view
    }
}

private class ListRowHolder(row: View?) {
    val tvName: TextView = row?.findViewById(R.id.tvName) as TextView
    val tvEmail: TextView = row?.findViewById(R.id.tvEmail) as TextView
    val tvId: TextView = row?.findViewById(R.id.tvId) as TextView
    val linearLayout: LinearLayout = row?.findViewById(R.id.linearLayout) as LinearLayout

}