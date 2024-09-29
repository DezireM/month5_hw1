package com.example.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.mvp.LoveContract
import com.example.myapplication.network.LoveResult
import com.example.myapplication.mvp.LovePresenter
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoveCalculatorBinding
import com.example.myapplication.mvp.LoveModel

class LoveCalculatorFragment : Fragment(), LoveContract.View {

    private val binding by lazy {
        FragmentLoveCalculatorBinding.inflate(layoutInflater)
    }

    private lateinit var presenter: LoveContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = LovePresenter(this, LoveModel())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        btnCalc.setOnClickListener {
            val firstName = etFname.text.toString()
            val secondName = etSname.text.toString()

            if (firstName.isBlank() || secondName.isBlank()) {
                Toast.makeText(requireContext(), "Enter both names", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            presenter.calculateLovePercentage(firstName, secondName)
        }
    }
    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }


    override fun showResult(result: LoveResult) {
        val bundle = Bundle().apply {
            putString("firstName", result.firstName)
            putString("secondName", result.secondName)
            putInt("percentage", result.percentage.toIntOrNull() ?: 0)
        }
        val resultFragment = ResultFragment().apply {
            arguments = bundle
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, resultFragment)
            .addToBackStack(null).commit()
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}