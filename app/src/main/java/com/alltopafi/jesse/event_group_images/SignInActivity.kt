package com.alltopafi.jesse.event_group_images

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import java.util.*

/**
 * Created by Jesse on 2/3/18.
 */

class SignInActivity: AppCompatActivity() {

    val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            //we are signed in
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            //we are not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setTheme(R.style.AppTheme)
                            .setAvailableProviders(Arrays.asList(
                                    AuthUI.IdpConfig.EmailBuilder().build(),
                                    AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN)

        }





    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if(resultCode == Activity.RESULT_OK) {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                //we failed to login
            }
        }
    }
}

