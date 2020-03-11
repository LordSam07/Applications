package com.lordsam.locallanguageguide

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter


class WordAdapter :ArrayAdapter<Word>(){

    fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        return super.getView(position, convertView, parent!!)
    }

}