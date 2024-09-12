package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CounterContract {
    private val binding by lazy {
        ActivityMainBinding.inflate((layoutInflater))
    }

    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter.attachContract(this)

        binding.apply {
            btnIncrement.setOnClickListener {
                presenter.onIncrement()
            }

            btnDecrement.setOnClickListener {
                presenter.onDecrement()
            }

        }

    }

    override fun onDestroy() {
        presenter.detachContract() // Detach the contract
        super.onDestroy()
    }

    override fun showResult(count: Int) = with(binding) {
        tvCount.text = count.toString()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun changeTextColor(color: Int) {
        binding.tvCount.setTextColor(color)
    }
}