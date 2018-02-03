package com.alltopafi.jesse.event_group_images

/**
 * Created by Jesse on 1/31/18.
 */

class Post(){
    var AUTHOR : String = ""
    var IMAGE_URL : String = ""

    override fun equals(other: Any?): Boolean {
        return this.IMAGE_URL.equals(((other as Post).IMAGE_URL))
    }
}