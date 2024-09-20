package com.example.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.LoveContract
import com.example.myapplication.network.LoveModel
import com.example.myapplication.ViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoveCalculatorBinding
import dagger.hilt.android.AndroidEntryPoint

class LoveCalculatorFragment : Fragment(), LoveContract.View {

    private var _binding: FragmentLoveCalculatorBinding? = null
    private val binding get() = _binding!!
    private val presenter = ViewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoveCalculatorBinding.inflate(inflater, container, false)
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

            presenter.getPercentage(firstName, secondName)
        }
    }

    override fun showResult(result: LoveModel) {
        val percentage = result.percentage.toIntOrNull() ?: 0
        val bundle = Bundle().apply {
            putString("firstName", result.firstName)
            putString("secondName", result.secondName)
            putInt("percentage", percentage)
        }
        val resultFragment = ResultFragment().apply {
            arguments = bundle
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, resultFragment)
            .addToBackStack(null).commit()
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}