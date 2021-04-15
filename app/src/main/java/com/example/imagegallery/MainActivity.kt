package com.example.imagegallery

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imagegallery.Adapter.ImageAdapter
import com.example.imagegallery.Fragment.FullScreenImg
import com.example.imagegallery.Fragment.GridFragment
import com.example.imagegallery.Model.Image

class MainActivity : AppCompatActivity() {

    private var imagesList = ArrayList<Uri>()

    private val gridFragment = GridFragment()
    //private val fullScreenImgFragment = FullScreenImg()

    private final val MY_READ_PERMISSION_CODE: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_READ_PERMISSION_CODE)
        }

        imagesList = getPicturePaths()

        supportFragmentManager.beginTransaction().apply {
            val bundle: Bundle = Bundle()
            bundle.putParcelableArrayList("images", imagesList)
            gridFragment.arguments = bundle
            //fullScreenImgFragment.arguments = bundle
            replace(R.id.fragment_section, gridFragment)
            commit()
        }
    }

    private fun getPicturePaths(): ArrayList<Uri> {
        var picPaths = ArrayList<Uri>()
        var allImagesUri: Uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        var projection = arrayOf(
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media._ID
        )

        val imageSortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        var cursor: Cursor? = contentResolver.query(allImagesUri, projection, null, null, imageSortOrder)

        cursor.use {
            it?.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)

                while (it.moveToNext()) {
                    val id = it.getLong(idColumn)
                    val name = it.getString(nameColumn)
                    val size = it.getString(sizeColumn)
                    val date = it.getString(dateColumn)

                    val contentUri: Uri = ContentUris.withAppendedId(allImagesUri, id)
                    picPaths.add(contentUri)
                }
            } ?: kotlin.run {
                Log.e("TAG", "Cursor is null!")
            }
        }
        return picPaths
    }

    fun showFullScreen (position: Int) {
        supportFragmentManager.beginTransaction().apply {
            val bundle: Bundle = Bundle()
            val fullScreenImgFragment = FullScreenImg(position)
            bundle.putParcelableArrayList("images", imagesList)
            fullScreenImgFragment.arguments = bundle
            replace(R.id.fragment_section, fullScreenImgFragment)
            commit()
        }
    }

    fun showGridView () {
        supportFragmentManager.beginTransaction().apply {
            val bundle: Bundle = Bundle()
            bundle.putParcelableArrayList("images", imagesList)
            gridFragment.arguments = bundle
            replace(R.id.fragment_section, gridFragment)
            commit()
        }
    }


/*
    private var imagesList = ArrayList<Uri>()
    private lateinit var imagesAdapter: ImageAdapter


    private final val MY_READ_PERMISSION_CODE: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_READ_PERMISSION_CODE)
        }

        imagesList = getPicturePaths()

        val recyclerView: RecyclerView = findViewById(R.id.image_list)
        imagesAdapter = ImageAdapter(this, imagesList, this)
        val layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = imagesAdapter

        //prepareImages()

    }

    private fun getPicturePaths(): ArrayList<Uri> {
        var imagesList = ArrayList<Image>()
        var picPaths = ArrayList<Uri>()
        var allImagesUri: Uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        var projection = arrayOf(
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media._ID
        )

        val imageSortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

        var cursor: Cursor? = contentResolver.query(allImagesUri, projection, null, null, imageSortOrder)


        cursor.use {
            it?.let {
                val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
                val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)

                while (it.moveToNext()) {
                    val id = it.getLong(idColumn)
                    val name = it.getString(nameColumn)
                    val size = it.getString(sizeColumn)
                    val date = it.getString(dateColumn)

                    val contentUri: Uri = ContentUris.withAppendedId(allImagesUri, id)
                    picPaths.add(contentUri)
                }
            } ?: kotlin.run {
                Log.e("TAG", "Cursor is null!")
            }
        }
        return picPaths
    }

    override fun onImageClickListener(data: Uri) {
        //Toast.makeText(this, "XD", Toast.LENGTH_SHORT).show()
        var intent: Intent = Intent(this, FullScreenImg::class.java)
        startActivity(intent)

    }
*/
}



