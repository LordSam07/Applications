package com.lordsam.botnav

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.item_bottom_navigation.*


abstract class BaseActivity :AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        navigation1.setOnNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigation1.postDelayed({
            val itemId = item.itemId
            if (itemId == R.id.main) {
                startActivity(Intent(this, MainActivity::class.java))
            } else if (itemId == R.id.second) {
                startActivity(Intent(this, MainActivity2::class.java))
            }
            finish()
        }, 100)
        return true
    }

    private fun updateNavigationBarState() {
        val actionId: Int = getBottomNavigationMenuItemId()
        selectBottomNavigationBarItem(actionId)
    }

    private fun selectBottomNavigationBarItem(itemId: Int) {
        val item: MenuItem = navigation1.menu.findItem(itemId)
        item.isChecked = true
    }

    abstract fun getLayoutId() :Int

    abstract fun getBottomNavigationMenuItemId() :Int
}