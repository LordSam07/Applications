package com.lordsam.pharmasupport

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var firebaseAnalytics: FirebaseAnalytics? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseDatabase :FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        var myRef = firebaseDatabase!!.getReference("message")
        myRef.setValue("Trying")

        loginButton.setOnClickListener {
            val email = userNameLogin.text.toString()
            val password = passwordLogin.text.toString()
            login(email, password)

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        textViewForgotPass.setOnClickListener {
//            val intent = Intent(this, ForgotPassActivity::class.java)
//            startActivity(intent)
        }

        buttonRegister.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = firebaseAuth!!.currentUser
        if (currentUser != null) {
            Toast.makeText(this, "Exists", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(email: String, password: String) {

        //verify-email.org
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (email.isEmpty()) {
            Toast.makeText(applicationContext, "enter email address", Toast.LENGTH_SHORT).show()
        } else {
            if (email.trim().matches(emailPattern.toRegex())) {
                Toast.makeText(applicationContext, "valid email address", Toast.LENGTH_SHORT).show()

                firebaseAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Invalid email address", Toast.LENGTH_SHORT).show()
            }
        }

        //verify password
        val passwordPattern = "[a-zA-Z0-9]+"

        if (password.isEmpty()){
            Toast.makeText(applicationContext, "enter password", Toast.LENGTH_SHORT).show()
        }else{
            if (password.length < 8 || !password.trim().matches(passwordPattern.toRegex())){
                Toast.makeText(applicationContext, "enter valid password", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "valid password", Toast.LENGTH_SHORT).show()
            }
        }

    }






}
