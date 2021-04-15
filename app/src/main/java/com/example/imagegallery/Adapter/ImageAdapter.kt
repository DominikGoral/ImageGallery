package com.example.imagegallery.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegallery.ImageClickListener
import com.example.imagegallery.Model.Image
import com.example.imagegallery.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_item.*

class ImageAdapter(private val context: Context, private val listImage: ArrayList<Uri>, private val imageClickListener: ImageClickListener) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {

        val path = listImage[position]

        Picasso.get()
            .load(path)
            .resize(300,300)
            .centerCrop()
            .into(holder.img)

        holder.itemView.setOnClickListener {
            //imageClickListener.onImageClickListener(path)
            imageClickListener.onItemClickListener(position)
        }

    }

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var img: ImageView = itemsView.findViewById(R.id.image)
    }

}