package com.ict2105.ict2105_team04_2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class ViewItemDetailsActivity : AppCompatActivity() {

    var sampleImages = intArrayOf(
        R.drawable.clothes,
        R.drawable.clothes,
        R.drawable.clothes
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item_details)

        supportActionBar?.title = "Item Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val carouselView = findViewById<CarouselView>(R.id.carouselView)
        carouselView.setPageCount(sampleImages.size)
        carouselView.setImageListener(imageListener)
    }

    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            imageView.setImageResource(sampleImages[position])
        }
    }
}
