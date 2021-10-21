package com.cesarvaliente.slidingpanelayout

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.cesarvaliente.slidingpanelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedVM: SharedVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListItemsFragment())
            .commit()

        // TODO: addCallback for onBackPressed

        sharedVM.selectedItem.observe(this, {
            // TODO: Change Fragments
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailFragment())
                .addToBackStack(null)
                .commit()
        })
    }

    // TODO: Handle SlidingPaneLayout onBackPressed
}
