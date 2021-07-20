package com.example.tomato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var  auth : FirebaseAuth

    lateinit var emailEditText: EditText
    lateinit var passworEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        emailEditText = findViewById(R.id.emailE)
        passworEditText = findViewById(R.id.passE)
    }


private fun createAccount(email: String,password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
            if(task.isSuccessful){
                Log.d("MainActivity","Created New User $email")
                Toast.makeText(baseContext, "Account Creation Successful",
                    Toast.LENGTH_SHORT).show()

            }
            else{
                Log.d("MainActivity", "Account creation failed", task.exception)
                Toast.makeText(baseContext, "Already a User",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun buttonClick(view: View) {
        when (view.id) {
            R.id.signUB -> {
                val email = emailEditText.text.toString()
                val password = passworEditText.text.toString()

                createAccount(email, password)
            }
            R.id.signIB ->{
                val email = emailEditText.text.toString()
                val password = passworEditText.text.toString()

                signIn(email, password)

            }
            R.id.mapB -> {
                val i = Intent(this,MapsActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Log.d("MainActivity","Sign In complete")
                Toast.makeText(baseContext, "Authentication Successful",
                    Toast.LENGTH_SHORT).show()
              //  newActivity(auth.currentUser?.email)
            }
            else{
                Log.d("MainActivity", "Account creation failed", it.exception)
                Toast.makeText(baseContext, "Authentication failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }



}



