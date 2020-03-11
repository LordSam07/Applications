package com.lordsam.pharmasupport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private var firebaseAuth :FirebaseAuth? = null
    private  var database: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        buttonSignUp.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val userName = editTextUserName.text.toString()
            val contact = editTextContact.text.toString().toInt()
            val rePassword = editTextRePassWord.text.toString()
            signUp(email, password, rePassword, userName, contact)
        }
    }

    private fun signUp(email :String, password :String, rePassword :String, userName :String, contact :Int){

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val passwordPattern = "[a-zA-Z0-9]+"

        //Check user name
        if (userName.isEmpty()){
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show()
        }else{

        }

        //Check email
        if (email.isEmpty()){
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
        }else{
            if (email.trim().matches(emailPattern.toRegex())){
                Toast.makeText(this, "Valid Email", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Enter valid email", Toast.LENGTH_SHORT).show()
            }
        }

        //Check password
        if (password.isEmpty()){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
        }else if (rePassword.isEmpty()){
            Toast.makeText(this, "Re-Enter password", Toast.LENGTH_SHORT).show()
        }else{
            if (password.length < 8 || !password.trim().matches(passwordPattern.toRegex())){
                Toast.makeText(this, "Enter valid password", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Valid password", Toast.LENGTH_SHORT).show()
            }
        }



        firebaseAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                Log.i("fbase","added")
            }else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
