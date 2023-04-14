package com.example.catchat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.catchat.R
import com.google.android.material.appbar.MaterialToolbar

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

        // now build a configuration that links the toolbar to the navigation graph
        val builder = AppBarConfiguration.Builder(navController.graph) //builder pattern
        val appBarConfiguration = builder.build()

        // apply configuration to the toolbar
        toolbar.setupWithNavController(navController,appBarConfiguration)
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