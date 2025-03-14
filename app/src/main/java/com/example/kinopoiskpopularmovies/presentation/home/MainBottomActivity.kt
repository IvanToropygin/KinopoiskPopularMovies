package com.example.kinopoiskpopularmovies.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoiskpopularmovies.R
import com.example.kinopoiskpopularmovies.databinding.ActivityMainBottomBinding

class MainBottomActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main_bottom) as NavHostFragment

        navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.movieDetailsFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        binding.navView.animate().translationY(binding.navView.height.toFloat()).duration = 300
        binding.navView.isGone = true
    }

    private fun showBottomNav() {
        binding.navView.isVisible = true
        binding.navView.animate().translationY(0f).duration = 300
    }
}