package com.example.hogwarts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.hogwarts.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.FirstFragment, R.id.characterFragment, R.id.housesFragment, R.id.spellsFragment
            ), binding.drawerlayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navigationview.setupWithNavController(navController)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerlayout.closeDrawer(GravityCompat.START)
                } else {
                    if(!navController.navigateUp())
                        finish()
                }
            }
        })

        val navigationView: NavigationView = findViewById(R.id.navigationview)
        val headerView = navigationView.getHeaderView(0)
        val usuario_header = headerView.findViewById<TextView>(R.id.usuario_header)

        usuario_header.text = "Pure Blood"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_exit -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Close App")
                builder.setMessage("Are you sure you want to close the app?")
                builder.setPositiveButton("Yes") { _, _ ->
                    finish()
                }
                builder.setNegativeButton("No", null)
                val dialog = builder.create()
                dialog.show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}