package com.example.kinopoiskpopularmovies.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.ActivityMainBottomBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainBottomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main_bottom) as NavHostFragment
        val navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navView
        navView.setupWithNavController(navController)

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.navigation_home, R.id.navigation_favourites)
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}