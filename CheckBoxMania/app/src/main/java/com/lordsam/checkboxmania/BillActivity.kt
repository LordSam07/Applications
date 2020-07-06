package com.lordsam.checkboxmania

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bill.view.*
import kotlinx.android.synthetic.main.card_item.view.*

class BillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)

        val bundle = intent.extras!!
        val listOfItems = bundle.getParcelableArrayList<Item>("item")
        val gst = bundle.getString("gst")
        val amount = bundle.getString("amount")
        val lv = findViewById<ListView>(R.id.lisView)
        val txtItem = findViewById<TextView>(R.id.textViewItems)
        val txtWeight = findViewById<TextView>(R.id.textViewWeight)
        val txtTotal = findViewById<TextView>(R.id.textViewTotal)
        var count = 0

        if (listOfItems != null) {
            val listAdapter = ListAdapter(this, listOfItems)
            lv.adapter = listAdapter
        }

        if (listOfItems != null) {
            txtItem.text = "Items :${listOfItems.size}"
            for (i in listOfItems){
                count += i.itemSize
            }
            txtWeight.text = count.toString()
            txtTotal.text = "Rs.$amount($gst GST)"
        }
    }

    class ListAdapter(context :Context, list :ArrayList<Item>) :BaseAdapter(){
        private var mctx :Context = context
        private var myList : ArrayList<Item> = list

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val item = myList[p0]
            val inflater = mctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val cardView = inflater.inflate(R.layout.card_item, null, true)
            cardView.textViewItemsCard.text = item.itemName
            cardView.textViewPriceCard.text = item.itemPrize.toString()
            cardView.textViewAmountCard.text = item.itemSize.toString()
            cardView.textViewTotalCard.text = item.totalAmount.toString()
            return  cardView
        }

        override fun getItem(p0: Int): Any {
            return "item"
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return myList.size
        }

    }
}