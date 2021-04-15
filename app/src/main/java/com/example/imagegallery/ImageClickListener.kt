package com.example.imagegallery

import android.net.Uri

interface ImageClickListener {
    fun onImageClickListener(data: Uri)
    fun onItemClickListener(position: Int)
    fun onFullScreenImageClickListener()
}