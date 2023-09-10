package com.example.devright_stillbaaitourism

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.devright_stillbaaitourism.databinding.ActivityEatBinding
import com.google.android.material.navigation.NavigationView

class Eat : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityEatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // ----------------------- List View ----------------------- //
        val listView = findViewById<ListView>(R.id.listView)

        // List items
        val items = listOf(
            ListItem(R.drawable.thumbail, "Pizza World", "Fun in the sun with Pizza"),
            ListItem(R.drawable.thumbail, "Item 2", "Description for Item 2"),
        )

        val adapter = CustomAdapter(this, items)
        listView.adapter = adapter

        // ----------------------- END List View ----------------------- //

        val menuBtn = findViewById<ImageButton>(R.id.btnMenu)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // Open drawer on menu button clicked
        menuBtn.setOnClickListener(){
            drawerLayout.open()
        }
        ///--------------------------------------------------------------------//

        binding.navView.bringToFront()
        binding.navView.setNavigationItemSelectedListener(this)

        ///--------------------------------------------------------------------///

        // Temporary card display
        //val linearLayout = findViewById<LinearLayout>(R.id.linearEatListings);
       // linearLayout.removeAllViews()

        /*for (i in 1..5)
        {
            val customCard = custom_card(this)

            linearLayout.addView(customCard)

        }*/

    }

    //............................................................................................//

    /// It will allow the user to navigate through pages.
    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        // When the activity pages are ready, uncomment the below code
        when(item.itemId)
        {
            /*R.id.nav_home -> {
                val intent = Intent(applicationContext, Home::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }

            R.id.nav_stay -> {
                val intent = Intent(applicationContext, Stay::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }

            R.id.nav_eat -> {
                val intent = Intent(applicationContext, Eat::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }*/
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        // return true marks the item as selected
        return true
    }

    //............................................................................................//

    /// Opens/closses the navigation drawer.
    override fun onBackPressed()
    {
        //if the drawer is open, close it
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        {
            //otherwise, let the super class handle it
            super.onBackPressed()
        }
    }

    //............................................................................................//

    override fun onClick(v: View?) {
        /*TODO("Not yet implemented")*/
    }

    //............................................................................................//

}