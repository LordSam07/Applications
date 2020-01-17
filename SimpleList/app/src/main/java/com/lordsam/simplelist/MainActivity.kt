package com.lordsam.simplelist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_list_ticket.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    var list = ArrayList<List>()
    var adapter :ListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add(List("",null))

        adapter = ListAdapter(this, list)
        toDoList.adapter = adapter

    }


    fun adder(index :Int){
        list.add(list[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class ListAdapter : BaseAdapter {

        var list = ArrayList<List>()
        var context : Context? = null

        constructor(context : Context, list :ArrayList<List>) :super(){
            this.list = list
            this.context = context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val work = list[position]
            var inflator =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflator.inflate(R.layout.activity_list_ticket, null)
            myView.textView.text = work.name
            myView.add.setOnClickListener {
                val intent = Intent(context, listTicket::class.java)
                intent.putExtra("name", work.name!!)
                context!!.startActivity(intent)

                add.setOnClickListener {
                    adder(position)
                }
            }
            return myView
        }

        override fun getItem(position: Int): Any {
            return list[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return list.size
        }

    }


}
