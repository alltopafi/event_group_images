package com.alltopafi.jesse.event_group_images

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alltopafi.jesse.event_group_images.constant.Constants
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_row.view.*

/**
 * Created by Jesse on 1/30/18.
 */

class MainAdapter(val posts: ArrayList<Post?>, val context: Context): RecyclerView.Adapter<CustomViewHolder>(){


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
//        holder?.view?.imageView_uploadedImage?.setImageBitmap(getBitmapFromFirebaseStorage(posts[position]?.IMAGE_URL)
        getBitmapFromFirebaseStorage(posts[position]?.IMAGE_URL, context, holder?.view?.imageView_uploadedImage )

    }

    private fun getBitmapFromFirebaseStorage(url: String?, context: Context, imageView: ImageView?) {

//        FirebaseStorage.getInstance().reference.child(Constants.ROOT_NODE).child(Constants.EVENT_NODE).child(url)
//                .getBytes(1024*1024).addOnCompleteListener {
                    Picasso.with(context)
                            .load(url)
                            .error(R.drawable.common_google_signin_btn_icon_dark)
                            .into(imageView)
//                }

    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view)