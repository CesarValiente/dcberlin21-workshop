package com.cesarvaliente.navigationrail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoRepository
import androidx.window.layout.WindowInfoRepository.Companion.windowInfoRepository
import androidx.window.layout.WindowLayoutInfo
import com.cesarvaliente.navigationrail.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigationrail.NavigationRailView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var windowInfoRepo: WindowInfoRepository
    private val scope = MainScope()

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navRailView: NavigationRailView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        windowInfoRepo = windowInfoRepository()

        //Bind components
        bottomNavView = binding.bottomNavView
        navRailView = binding.navigationRail
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        setupNavigation()
        adjustUI()
    }

    private fun setupNavigation() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun adjustUI() {
        scope.launch {
            windowInfoRepo.windowLayoutInfo
                .collect { value ->
                    showUI(value)
                }
        }
    }

    private fun showUI(windowLayoutInfo: WindowLayoutInfo) {
        if (windowLayoutInfo.displayFeatures.isEmpty()) {
            bottomNavView.setupWithNavController(navController)
            bottomNavView.visibility = View.VISIBLE
            navRailView.visibility = View.GONE
        } else {
            (windowLayoutInfo.displayFeatures.component1() as? FoldingFeature)?.apply {
                if (isSeparating) {
                    navRailView.setupWithNavController(navController)
                    navRailView.visibility = View.VISIBLE
                    bottomNavView.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}