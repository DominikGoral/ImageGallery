package com.example.imagegallery.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.imagegallery.ImageClickListener
import com.example.imagegallery.R
import com.squareup.picasso.Picasso

class ImageSliderAdapter(var images: ArrayList<Uri>, var ctx: Context, private val imageClickListener: ImageClickListener): PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        layoutInflater = LayoutInflater.from(ctx)
        var view = layoutInflater.inflate(R.layout.full_screen_img, container, false)
        val img: ImageView = view.findViewById<ImageView>(R.id.fullimg)
        img.setImageURI(images[position])
        img.setOnClickListener {
            imageClickListener.onFullScreenImageClickListener()
        }
        container.addView(view)

        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}