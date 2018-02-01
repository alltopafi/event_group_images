package com.alltopafi.jesse.event_group_images

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        recyclerView_main.layoutManager = LinearLayoutManager(this )
        recyclerView_main.adapter = MainAdapter()

    }
}
