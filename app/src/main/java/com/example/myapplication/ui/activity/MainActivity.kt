package com.example.myapplication.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.LoveContract
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragments.LoveCalculatorFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.count.observe(this) { count ->
            binding.tvCount.text = count.toString()
        }

        viewModel.toastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.textColor.observe(this) { color ->
            binding.tvCount.setTextColor(color)
        }
        binding.apply {
            btnIncrement.setOnClickListener {
                viewModel.increment()
            }
            btnDecrement.setOnClickListener {
                viewModel.decrement()
            }
        }
    }
}