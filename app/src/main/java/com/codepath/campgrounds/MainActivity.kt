package com.codepath.campgrounds

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Headers

private const val TAG = "MainActivity"

// Use BuildConfig.API_KEY (loaded from apikey.properties)
private val PARKS_API_KEY = BuildConfig.API_KEY
private val CAMPGROUND_URL =
    "https://developer.nps.gov/api/v1/campgrounds?api_key=${PARKS_API_KEY}"

class MainActivity : AppCompatActivity() {

    private val campgrounds = mutableListOf<Campground>()
    private lateinit var campgroundAdapter: CampgroundAdapter
    private lateinit var campgroundsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up RecyclerView
        campgroundsRecyclerView = findViewById(R.id.campgrounds)
        campgroundAdapter = CampgroundAdapter(this, campgrounds)
        campgroundsRecyclerView.adapter = campgroundAdapter
        campgroundsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch campground data
        fetchCampgrounds()
    }

    private fun fetchCampgrounds() {
        val client = AsyncHttpClient()
        client.get(CAMPGROUND_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch campgrounds: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched campgrounds: $json")
                try {
                    // Parse JSON response into CampgroundResponse object
                    val parsedJson = Json { ignoreUnknownKeys = true }.decodeFromString<CampgroundResponse>(
                        json.jsonObject.toString()
                    )

                    // Add campgrounds and refresh RecyclerView
                    parsedJson.data?.let { list ->
                        campgrounds.clear()
                        campgrounds.addAll(list)
                        campgroundAdapter.notifyDataSetChanged()
                    }

                } catch (e: Exception) {
                    Log.e(TAG, "Exception parsing JSON", e)
                }
            }
        })
    }
}
