package com.example.hogwarts.ui

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hogwarts.R
import com.example.hogwarts.databinding.ActivityMainBinding
import com.example.hogwarts.databinding.FragmentFirstBinding
import com.example.hogwarts.ui.fragments.easterEgg.ResultadoFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), ResultadoFragment.OnResultadoListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var headerTextView: TextView
    private lateinit var headercasaTextView: TextView
    private lateinit var headerImage: ImageView
    private var contador = 0



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
        val headercasaView = navigationView.getHeaderView(0)
        headerTextView = headerView.findViewById(R.id.usuario_header)
        headercasaTextView = headercasaView.findViewById(R.id.casa_header)
        headerImage = headerView.findViewById(R.id.image_galeria)

        headerTextView.text = "Half-Blood"

        if (contador <= 10) {
            headerImage.setOnClickListener {
                contador++

                if(contador == 10){
                    headerTextView.text = "Pure Blood"
                    Toast.makeText(this, "Congratulations you have promoted to pure blood!!", Toast.LENGTH_SHORT).show()
                }
            }
        }


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

    override fun onResultadoCasaSeleccionada(resultadoCasa: String) {
        // Aquí puedes actualizar el textView de la casa con el resultadoCasa
        headercasaTextView.text = resultadoCasa
    }


}