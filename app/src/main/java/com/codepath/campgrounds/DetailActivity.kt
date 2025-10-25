package com.codepath.campgrounds

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "CampgroundDetailActivity"
const val CAMPGROUND_EXTRA = "CAMPGROUND_EXTRA"

class DetailActivity : AppCompatActivity() {
    private lateinit var campgroundNameTV: TextView
    private lateinit var campgroundDescriptionTV: TextView
    private lateinit var campgroundLatLongTV: TextView
    private lateinit var campgroundImageIV: ImageView

    companion object {
        const val CAMPGROUND_EXTRA = "CAMPGROUND_EXTRA"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the remaining Views for the screen
        campgroundNameTV = findViewById(R.id.campgroundName)


        // TODO: Get the extra from the Intent


        // TODO:  Set the name, location, and description information


        // TODO: Load the image using Glide

    }
}
