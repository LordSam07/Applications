package com.lordsam.broadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        when(intent.action){

            Intent.ACTION_POWER_DISCONNECTED -> {
                Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_HEADSET_PLUG -> {
                Toast.makeText(context, "HeadSet", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                Toast.makeText(context, "Airplane", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
