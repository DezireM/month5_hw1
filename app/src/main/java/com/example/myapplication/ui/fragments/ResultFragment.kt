package com.example.myapplication.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private val binding by lazy {
        FragmentResultBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val firstName = arguments?.getString("firstName")
        val secondName = arguments?.getString("secondName")
        val percentage = arguments?.getString("percentage")

        binding.tvYouResult.text = firstName
        binding.tvMeResult.text = secondName
        binding.tvProcent.text = "$percentage%"

        binding.btnTryAgain.setOnClickListener {
            val loveCalculatorFragment = LoveCalculatorFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, loveCalculatorFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}