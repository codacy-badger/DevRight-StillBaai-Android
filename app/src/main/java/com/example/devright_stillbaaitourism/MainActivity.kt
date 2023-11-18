package com.example.devright_stillbaaitourism

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var burgerMenu: BurgerMenu
    private val stilBaaiUrl: String = "https://stilbaaitourism.co.za/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        burgerMenu = BurgerMenu(this, R.layout.activity_main)
        burgerMenu.setupDrawer()

        val dbHandler = DBHandler();
        GlobalClass.StayDataList.clear()
        thread {
            dbHandler.getConnection()
            dbHandler.fetchActivityData()
            dbHandler.fetchContactData()
            dbHandler.fetchEatData()
            dbHandler.fetchBusinessData()
            dbHandler.fetchStayData()
            dbHandler.fetchListingData()
            dbHandler.fetchEventsData()
        }

        dbHandler.getConnection();

        val stilBaaiClick = findViewById<TextView>(R.id.btnStil)
        val jongensClick = findViewById<TextView>(R.id.btnJong)
        val melkClick = findViewById<TextView>(R.id.btnMelk)


        stilBaaiClick.setOnClickListener{
            val uri = Uri.parse(stilBaaiUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        jongensClick.setOnClickListener{
            val uri = Uri.parse(stilBaaiUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        melkClick.setOnClickListener{
            val uri = Uri.parse(stilBaaiUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }


        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val linearLayoutEvents = findViewById<LinearLayout>(R.id.linearEventsDisplay)

        // Fecthing Event List
        val eventList = GlobalClass.EventDataList
        // Create event fragments for each event
        val eventFragments = listOf(
            EventFragment.newInstance("Eel Feeding", "18:00", "Backyard, 2440"),
            EventFragment.newInstance("Dua Lipa", "19:00", "Open Field, 2440"),
            EventFragment.newInstance("Mamma Mia", "15:00", "Open Field, 2440"),
        )

        val advertFragment = listOf(
            AdvertFragment.newInstance("Come visit our store!", "https://fabricatecapetown.co.za/wp-content/uploads/2020/11/Fabricate_November-2019_006b-11-scaled.jpg"),
            AdvertFragment.newInstance("Come visit our store!", "https://fabricatecapetown.co.za/wp-content/uploads/2020/11/Fabricate_November-2019_006b-11-scaled.jpg")

        )

        // Create the FragmentPagerAdapter and set it to the ViewPager
        val adapter = EventPagerAdapter(supportFragmentManager, advertFragment)
        viewPager.adapter = adapter

        // Connect the TabLayout with the ViewPager for navigation
        tabLayout.setupWithViewPager(viewPager)
        val viewPagerr: ViewPager = findViewById(R.id.viewPager)

        val handler = Handler()

            // Set the initial delay and duration
        val initialDelay = 4000L  // 2 seconds delay before auto-scroll starts
        val scrollDuration = 6000L // 3 seconds duration for each scroll

        // Start auto-scrolling when the activity is created
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Get the current item index
                var currentItem = viewPagerr.currentItem

                // Calculate the next item index (circular scrolling)
                currentItem = (currentItem + 1) % adapter.getCount()

                // Scroll to the next item with smooth scroll
                viewPagerr.setCurrentItem(currentItem, true)

                // Schedule the next auto-scroll after the specified duration
                handler.postDelayed(this, scrollDuration)
            }
        }, initialDelay)


        for (event in eventList) {
            val eventView = layoutInflater.inflate(R.layout.events_home, null)

            eventView.findViewById<TextView>(R.id.eventName).text = event.EVENT_NAME ?: ""
            eventView.findViewById<TextView>(R.id.eventTime).text = event.EVENT_STARTTIME ?: ""
            eventView.findViewById<TextView>(R.id.eventLocation).text = event.EVENT_ADDRESS ?: ""

            linearLayoutEvents.addView(eventView)
        }




        // ------------------- End Test ------------------- //


    }

}