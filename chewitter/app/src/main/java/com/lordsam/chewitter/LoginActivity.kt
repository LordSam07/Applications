package com.lordsam.chewitter

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    var myFirebaseAnalytics :FirebaseAnalytics ?= null
    var myFirebaseAuth :FirebaseAuth ?= null
    var databse =FirebaseDatabase.getInstance()
    var myRef = databse.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        myFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        myFirebaseAuth = FirebaseAuth.getInstance()

        imageLoginProfile.setOnClickListener {
            checkPermission()
        }

        buttonLoginLogin.setOnClickListener {
            val email = editTextLoginEMail.text.toString().trim()
            val password = editTextLoginPass.text.toString().trim()
            signToFirebase(email, password)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode){
            readImageCode -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    loadImage()
                }else{
                    Toast.makeText(this, "Permission Denied!",
                    Toast.LENGTH_LONG).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pickImageCode && data != null  && resultCode == RESULT_OK) {//Activity.
            val selectImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(selectImage!!, filePathColumn, null, null, null)
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()
            imageLoginProfile.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }

    private val readImageCode = 67
    fun checkPermission(){
        if (Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), readImageCode)
                return
            }
        }

        loadImage()
    }

    private val pickImageCode = 76
    fun loadImage(){

        val intent = Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, pickImageCode)

    }

    fun signToFirebase(email :String, password :String){

        myFirebaseAuth!!.signInWithEmailAndPassword(email, password).
                addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        saveImageToFirebase()
                    }else{
                        Toast.makeText(applicationContext,
                        "Failed to sign in!",
                        Toast.LENGTH_LONG).show()
                    }
                }
    }

    fun saveImageToFirebase(){

        val currentUser = myFirebaseAuth!!.currentUser
        val firebaseStorage = FirebaseStorage.getInstance()
        val storageRef = firebaseStorage.getReferenceFromUrl("gs://chewitter-3f24c.appspot.com")
        val dateFormat = SimpleDateFormat("ddmmyyhhmmss")
        val dateObj = Date()
        val imagePath = dateFormat.format(dateObj) + ".jpg"
        val imageRef = storageRef.child("profile/$imagePath")
        imageLoginProfile.isDrawingCacheEnabled = true
        imageLoginProfile.buildDrawingCache()

        val drawable = imageLoginProfile.drawable as BitmapDrawable
        val bitmap = drawable.bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val data = byteArrayOutputStream.toByteArray()
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener{

            Toast.makeText(applicationContext,
                "Failed to save!",
                Toast.LENGTH_LONG).show()
        }.addOnSuccessListener { taskSnapshot ->
            val downloadURL = taskSnapshot.uploadSessionUri.toString()

            myRef.child("User").setValue(downloadURL)
        }

    }
}