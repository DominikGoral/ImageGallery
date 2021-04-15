package com.example.imagegallery.Fragment


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.imagegallery.Adapter.ImageSliderAdapter
import com.example.imagegallery.ImageClickListener
import com.example.imagegallery.MainActivity

import com.example.imagegallery.R
import kotlinx.android.synthetic.main.fragment_full_screen_img.*


/**
 * A simple [Fragment] subclass.
 *
 */
class FullScreenImg(val currentPosition: Int) : Fragment(), ImageClickListener {

    //var vpPosition = 0

    override fun onImageClickListener(data: Uri) {

    }

    override fun onItemClickListener(position: Int) {
        //vpPosition = position
    }

    override fun onFullScreenImageClickListener() {
        (activity as MainActivity?)!!.showGridView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val images = this.arguments!!.getParcelableArrayList<Uri>("images")
        val view =  inflater.inflate(R.layout.fragment_full_screen_img, container, false)
        var pagerAdapter = ImageSliderAdapter(images as ArrayList<Uri>, requireContext(), this)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = currentPosition
        return view
    }


}
