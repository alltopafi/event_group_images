package com.alltopafi.jesse.event_group_images

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Button
import com.alltopafi.jesse.event_group_images.constant.Constants
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
        recyclerView_main.adapter = MainAdapter(posts)


        val cameraButton = findViewById<Button>(R.id.button_camera)
        cameraButton.setOnClickListener {view ->

            val cameraViewIntent = Intent(this, ImageCaptureActivity::class.java)
            startActivity(cameraViewIntent)
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
                            posts.add(post)
                        }
                        recyclerView_main.adapter.notifyDataSetChanged()
                    }

                    override fun onCancelled(p0: DatabaseError?) {
                        Log.e("Error", p0.toString())
                    }
                })

        return posts
    }

}
