package com.example.myapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.LoveContract
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragments.LoveCalculatorFragment

class MainActivity : AppCompatActivity(), LoveContract {
    private val binding by lazy {
        ActivityMainBinding.inflate((layoutInflater))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoveCalculatorFragment())
                .commit()
        }

    }


}