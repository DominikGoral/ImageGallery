package com.example.imagegallery.Fragment


import android.net.Uri
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegallery.Adapter.ImageAdapter
import com.example.imagegallery.ImageClickListener
import com.example.imagegallery.MainActivity

import com.example.imagegallery.R

/**
 * A simple [Fragment] subclass.
 *
 */
class GridFragment : Fragment(), ImageClickListener {
    override fun onFullScreenImageClickListener() {

    }

    override fun onItemClickListener(position: Int) {
        (activity as MainActivity?)!!.showFullScreen(position)
    }

    private lateinit var imagesAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val images = this.arguments!!.getParcelableArrayList<Uri>("images")
        val view =  inflater.inflate(R.layout.fragment_grid, container, false)
        val recyclerView: RecyclerView = view!!.findViewById(R.id.image_list)
        imagesAdapter = ImageAdapter(requireContext(), images as ArrayList<Uri>, this)
        val layoutManager = GridLayoutManager(activity!!.applicationContext, 3)
        recyclerView.layoutManager = layoutManager as RecyclerView.LayoutManager?
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = imagesAdapter

        // Inflate the layout for this fragment
        return view
    }


    override fun onImageClickListener(data: Uri) {
        //(activity as MainActivity?)!!.showFullScreen()
    }


}
