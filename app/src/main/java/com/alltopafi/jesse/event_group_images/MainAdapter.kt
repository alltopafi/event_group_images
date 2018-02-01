package com.alltopafi.jesse.event_group_images

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.image_row.view.*

/**
 * Created by Jesse on 1/30/18.
 */

class MainAdapter(val posts: ArrayList<Post?>): RecyclerView.Adapter<CustomViewHolder>(){


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

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view)