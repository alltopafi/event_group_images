package com.alltopafi.jesse.event_group_images

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Button
import com.alltopafi.jesse.event_group_images.constant.Constants
import com.firebase.ui.auth.AuthUI
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this )
        val posts = firebaseRetrieve()
        recyclerView_main.adapter = MainAdapter(posts, this)


        val cameraButton = findViewById<Button>(R.id.button_camera)
        cameraButton.setOnClickListener {view ->

            val cameraViewIntent = Intent(this, ImageCaptureActivity::class.java)
            startActivity(cameraViewIntent)
        }

        val signoutButton = findViewById<Button>(R.id.button_signout)
        signoutButton.setOnClickListener {view ->
            signoutUser()
        }

    }

    private fun firebaseRetrieve(): ArrayList<Post?> {
        val posts = ArrayList<Post?>()

        FirebaseDatabase.getInstance().reference
                .child(Constants.ROOT_NODE).child(Constants.EVENT_NODE).child(Constants.POST_NODE).
                addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot?) {


                        for (postSnapshot in p0!!.children) {
                            Log.i("Key value for child", postSnapshot.key)

                            val post = p0.child(postSnapshot.key).getValue(Post::class.java)

                            if(!posts.contains(post)) {
                                posts.add(post)
                            }
                        }
                        recyclerView_main.adapter.notifyDataSetChanged()
                    }

                    override fun onCancelled(p0: DatabaseError?) {
                        Log.e("Error", p0.toString())
                    }
                })

        return posts
    }

    fun signoutUser() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener{
                    startActivity(Intent(this, SignInActivity::class.java))
                    finish()
                }
    }

}
