package com.example.myapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.LoveContract
import com.example.myapplication.R
import com.example.myapplication.SharedPreferenceHelper
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.network.LoveApiService
import com.example.myapplication.ui.fragments.LoveCalculatorFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), LoveContract {
    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferenceHelper
    @Inject
    lateinit var loveApiService: LoveApiService

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.setGraph(R.navigation.nav_graph)

        if (sharedPreferencesHelper.isOnBoardShown()) {
            navController.navigate(R.id.action_onBoardFragment_to_loveCalculatorFragment)
        }
    }


}