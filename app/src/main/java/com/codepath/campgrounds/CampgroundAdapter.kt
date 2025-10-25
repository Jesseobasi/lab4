package com.codepath.campgrounds


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


private const val TAG = "CampgroundAdapter"
// It's good practice to define constants like CAMPGROUND_EXTRA in a companion object
// or a dedicated constants file. Assuming it will be defined in DetailActivity for now.
// e.g. import com.codepath.campgrounds.DetailActivity.Companion.CAMPGROUND_EXTRA


class CampgroundAdapter(private val context: Context, private val campgrounds: List<Campground>) :
    RecyclerView.Adapter<CampgroundAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_campground, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val campground = campgrounds[position]
        holder.bind(campground)
    }


    override fun getItemCount() = campgrounds.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {


        private val nameTextView = itemView.findViewById<TextView>(R.id.campgroundName)
        private val descriptionTextView = itemView.findViewById<TextView>(R.id.campgroundDescription)
        private val locationTextView = itemView.findViewById<TextView>(R.id.campgroundLocation)
        private val imageView = itemView.findViewById<ImageView>(R.id.campgroundImage)


        init {
            itemView.setOnClickListener(this)
        }


        fun bind(campground: Campground) {
            nameTextView.text = campground.name
            descriptionTextView.text = campground.description
            locationTextView.text = campground.latLong


            Glide.with(context)
                .load(campground.imageUrl)
                .into(imageView)
        }


        override fun onClick(v: View?) {
            // Get selected campground
            val campground = campgrounds[absoluteAdapterPosition]


            //  Navigate to Details screen and pass selected campground
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.CAMPGROUND_EXTRA, campground) // Assuming CAMPGROUND_EXTRA is in DetailActivity.Companion
            context.startActivity(intent)
        }
    }
}
