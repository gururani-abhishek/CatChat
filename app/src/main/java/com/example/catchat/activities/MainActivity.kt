package com.example.catchat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.catchat.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // take a reference of navController from navHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
        as NavHostFragment
        val navController = navHostFragment.navController

        // get an instance of AppBarConfiguration.Builder() class
        val builder = AppBarConfiguration.Builder(navController.graph)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        builder.setOpenableLayout(drawerLayout)

        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)

        val bottomAppBar = findViewById<BottomNavigationView>(R.id.bottom_bar_nav)
        bottomAppBar.setupWithNavController(navController)

        val navViewDrawer = findViewById<NavigationView>(R.id.nav_view_drawer)
        navViewDrawer.setupWithNavController(navController)

    }

    /* when the activity is ready to add items to toolbar this method is called
     it inflates the menu resource file, and adds each item to the toolbar.
     you're overriding this method, and are inflating the menu_toolbar android resource file */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.helpFragment -> {
                val navController = findNavController(R.id.nav_host_fragment_container)
                item.onNavDestinationSelected(navController)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}