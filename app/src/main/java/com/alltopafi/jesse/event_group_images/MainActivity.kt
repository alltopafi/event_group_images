package com.alltopafi.jesse.event_group_images

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
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

    }

    private fun firebaseRetrieve(): ArrayList<Post?> {
        val posts = ArrayList<Post?>()

        FirebaseDatabase.getInstance().reference
                .child(Constants.ROOT_NODE).child(Constants.EVENT_NODE).child(Constants.POST_NODE).
                                                    addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {


                for(postSnapshot in p0!!.children) {
                    Log.i("Key value for child", postSnapshot.key)

                    val post = p0.child(postSnapshot.key).getValue(Post::class.java)
                    posts.add(post)
                }
            }

            override fun onCancelled(p0: DatabaseError?) {
                Log.e("Error", p0.toString())
            }
        })

        return posts
    }

}
