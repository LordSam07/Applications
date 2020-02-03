package com.lordsam.easynotes

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listNote = ArrayList<Note>()
    var noteAdapter = NoteAdapter(listNote)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listNote.add(Note(1, "Sam", " My name"))
        listView.adapter = noteAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        //Search
        val searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.addNote -> {
                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class NoteAdapter(var listNote: ArrayList<Note>) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.activity_ticket, null)
            var note = listNote[position]
            myView.textView.text = note.noteTitle
            myView.textView2.text = note.noteInfo
            return myView
        }

        override fun getItem(position: Int): Any {
            return listNote[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNote.size
        }
    }
}
