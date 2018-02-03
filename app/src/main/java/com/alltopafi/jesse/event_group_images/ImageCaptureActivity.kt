package com.alltopafi.jesse.event_group_images

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView



/**
 * Created by Jesse on 2/2/18.
 */

class ImageCaptureActivity: AppCompatActivity() {

    private var imageView: ImageView? = null

    private val CAMERA_REQUEST_CODE = 1888

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagecapture)
        imageView = findViewById<ImageView>(R.id.imageView1)
        val photoButton = findViewById<Button>(R.id.button1)

        photoButton.setOnClickListener { view ->

            checkForCameraPermission(this)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            var photo = data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(photo)
        }
    }


    private fun checkForCameraPermission(context: Context) {
        val cameraPermission = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)

        if(cameraPermission != PackageManager.PERMISSION_GRANTED) {
            Log.i("Error", "Permission not granted for camera asking for permission")
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)

        }else{
            var cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.e("Error", "Permission has not been granted")
                } else {
                    Log.e("Request", "Permission has been granted")

                    var cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
                }
            }
        }
    }

}












