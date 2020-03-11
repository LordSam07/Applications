package com.lordsam.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var fdb = FirebaseDatabase.getInstance()
    private var ref = fdb.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

        buttonlogin.setOnClickListener {
            loginFun(editTextUSer.text.toString(), editTextPass.text.toString())
        }

        ref.setValue("assfjjasjb")
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        loadGame()
    }


    fun loginFun(user :String, pass :String){
        mAuth!!.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                Toast.makeText(applicationContext, "done", Toast.LENGTH_SHORT).show()
                var currentUser = mAuth!!.currentUser

                if (currentUser!=null)
                    ref.child("New").child(strSplit(currentUser.email)).setValue(currentUser.uid )
                loadGame()
            }else{
                Toast.makeText(applicationContext, "fail", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun loadGame(){
        var currentUser = mAuth!!.currentUser
        //save to db
        if (currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user", currentUser.email)
            intent.putExtra("uid", currentUser.uid)
            startActivity(intent)
        }
    }

    fun strSplit(str :String?) :String{
        val split = str!!.split("@")
        return split[0]
    }

}
