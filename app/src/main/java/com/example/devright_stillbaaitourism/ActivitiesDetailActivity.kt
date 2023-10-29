package com.example.devright_stillbaaitourism

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class ActivitiesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activities)

        val activityName = intent.getStringExtra("ActivityName")
        val description = intent.getStringExtra("Description")
        val imageUrls = intent.getStringArrayListExtra("imageUrls")
        val contactNumber = intent.getStringExtra("ContactNumber")
        val websiteLink = intent.getStringExtra("WebsiteLink")

        val tvActName = findViewById<TextView>(R.id.tvActivityName)
        val tvDescription = findViewById<TextView>(R.id.descriptionTextView)
        val imagesDisplay = findViewById<ImageSlider>(R.id.imageSlider)
        val tvContactNumber = findViewById<TextView>(R.id.tvContactNumber)
        val tvWebsiteLink = findViewById<TextView>(R.id.tvWebsiteURL)

        tvActName.text = activityName
        tvDescription.text = Html.fromHtml(description)
        tvContactNumber.text = contactNumber
        tvWebsiteLink.text = websiteLink

        // Create a list of SlideModel to hold image data
        val slideModels = ArrayList<SlideModel>()

        // Check if imageUrls is not null and load images into slideModels
        if (imageUrls != null) {
            for (imageUrl in imageUrls) {
                slideModels.add(SlideModel(imageUrl))
            }
        }

        // Set the slideModels as the data source for ImageSlider
        imagesDisplay.setImageList(slideModels, ScaleTypes.FIT)
    }
}