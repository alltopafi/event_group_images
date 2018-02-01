package com.alltopafi.jesse.event_group_images

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alltopafi.jesse.event_group_images.constant.Constants
import com.google.firebase.FirebaseException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.image_row.view.*

/**
 * Created by Jesse on 1/30/18.
 */

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val posts = firebaseRetrieve()



    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.image_row, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        holder?.view?.textView_authorName?.text = posts[position]?.AUTHOR

    }
}

 private fun firebaseRetrieve(): ArrayList<Post?> {
    val posts = ArrayList<Post?>()

     FirebaseDatabase.getInstance().reference
             .child(Constants.ROOT_NODE).addValueEventListener(object : ValueEventListener{
         override fun onDataChange(p0: DataSnapshot?) {
            val post = p0?.getValue(Post::class.java)
            posts.add(post)
         }

         override fun onCancelled(p0: DatabaseError?) {
             Log.e("Error", p0.toString())
         }
     })
     return posts
 }

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view)