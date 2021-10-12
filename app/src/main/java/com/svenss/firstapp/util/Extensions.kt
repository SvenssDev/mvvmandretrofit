package com.svenss.firstapp.util

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by miguelleon on 12,octubre,2021
 */

fun ImageView.loadImage(uri: String){
    Glide.with(this.context).load(uri).into(this)
}