package com.lordsam.easynotes

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RemoteViews
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ticket.view.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager :NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder :Notification.Builder
    private val channelID = "com.lordsam.easynotes"
    private val des = "Test Notification"

    private var listNote = ArrayList<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //listNote.add(Note(1, "Sam", " My name"))
        loadQuery("%")
    }

    override fun onResume() {
        super.onResume()
        loadQuery("%")
    }

    fun loadQuery(title: String) {
        val dbManager = DatabaseManager(this)
        val selectionArgs = arrayOf(title)
        val projections = arrayOf("ID", "Title", "Info")
        val cursor = dbManager.query(projections, "Title like ?", selectionArgs, "Title")
        listNote.clear()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val titles = cursor.getString(cursor.getColumnIndex("Title"))
                val info = cursor.getString(cursor.getColumnIndex("Info"))
                listNote.add(Note(id, titles, info))
            } while (cursor.moveToNext())
        }
        val noteAdapter = NoteAdapter(this, listNote)
        listView.adapter = noteAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        //Search
        val searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                loadQuery("%$query%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.addNote -> {
                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class NoteAdapter(private var context: Context, private var listNote: ArrayList<Note>) :
        BaseAdapter() {

        @SuppressLint("ViewHolder", "InflateParams")
        @RequiresApi(Build.VERSION_CODES.O)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.activity_ticket, null)
            val note = listNote[position]
            myView.textView.text = note.noteTitle
            myView.textView2.text = note.noteInfo
            myView.imageView.setOnClickListener {
                val dbManager = DatabaseManager(this.context)
                val selectionArgs = arrayOf(note.noteId.toString())
                dbManager.delete("ID = ?", selectionArgs)
                loadQuery("%")
            }
            myView.imageView2.setOnClickListener {
                gotUpdate(note)
            }
            myView.imageViewPin.setOnClickListener {
                val title = myView.textView.text.toString()
                addNotification(title)
            }
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

    fun gotUpdate(note: Note) {

        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("ID", note.noteId)
        intent.putExtra("Title", note.noteTitle)
        intent.putExtra("Info", note.noteInfo)
        intent.putExtra("actionBar","edit")
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNotification(title :String) {

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent =  Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val contentView = RemoteViews(packageName, R.layout.activity_notification)
        contentView.setTextViewText(R.id.textViewNotify, title)

//        val mainIntent = Intent(this, MainActivity::class.java)
//        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        val mainPendingIntent = PendingIntent.getActivity(this, 0 ,mainIntent, PendingIntent.FLAG_ONE_SHOT)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationChannel =
                NotificationChannel(channelID, des, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelID)
                .setContent(contentView)
                .setSmallIcon(R.mipmap.ic_app_icon)
                .setContentIntent(pendingIntent)
        }else{

            builder = Notification.Builder(this)
                .setContent(contentView)
                .setSmallIcon(R.mipmap.ic_app_icon)
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())

    }
}
