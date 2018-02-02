package com.alltopafi.jesse.event_group_images

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.alltopafi.jesse.event_group_images.constant.Constants
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Jesse on 2/1/18.
 */

class SignUpActivity: AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        var emailField = findViewById<EditText>(R.id.editText_email_signup)
        var passwordField = findViewById<EditText>(R.id.editText_password_signup)
        var confirmPasswordField = findViewById<EditText>(R.id.editText_confirmPassword_signup)

        var btnLogin = findViewById<Button>(R.id.button_signup)
        btnLogin.setOnClickListener { view ->

            if(emailField.text.toString().isNullOrBlank()){
                "You must give an email".toast(this)
            }else {
                if (matchingPasswords(passwordField.text.toString(), confirmPasswordField.text.toString())) {
                signIn(fbAuth, emailField?.text.toString(), passwordField?.text.toString(), this)
                } else {
                    "Passwords must match".toast(this)
                }
            }

        }
    }


    private fun matchingPasswords(password: String, confirm: String): Boolean {
        if(password.isNullOrBlank() || confirm.isNullOrBlank()) {
            return false
        }

        if(!password.equals(confirm)){
            return false
        }

        return true
    }



}

fun signIn(fbAuth: FirebaseAuth, email: String, password: String, context: Context) {
    fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
        if (task.isSuccessful) {
            //Registration OK
//            val firebaseUser = fbAuth.currentUser!!

            var intent = Intent(context, MainActivity::class.java)
            startActivity(context, intent, null)


        } else {
            Constants.CREATE_ACT_ERROR.toast(context)
        }
    }
}

fun Any.toast(context: Context) {
    Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()
}