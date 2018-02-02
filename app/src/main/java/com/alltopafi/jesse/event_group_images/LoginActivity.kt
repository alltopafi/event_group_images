package com.alltopafi.jesse.event_group_images

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Jesse on 2/1/18.
 */



class LoginActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var emailField = findViewById<EditText>(R.id.editText_email)
        var passwordField = findViewById<EditText>(R.id.editText_password)

        var btnLogin = findViewById<Button>(R.id.button_login)
        btnLogin.setOnClickListener { view ->
            signIn(view, emailField?.text.toString(), passwordField?.text.toString())
        }
    }

    fun signIn(view: View,email: String, password: String){
//        showMessage(view,"Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
//                var intent = Intent(this, LoggedInActivity::class.java)
//                intent.putExtra("id", fbAuth.currentUser?.email)
//                startActivity(intent)LoggedInActivity::class.java)
//                intent.putExtra("id", fbAuth.currentUser?.email)
//                startActivity(intent)
                Log.i("status", "worked--------------")

            }else{
//                showMessage(view,"Error: ${task.exception?.message}")
                Log.e("Login Error", "failed to login")
            }
        })

    }
}
