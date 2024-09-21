package com.example.myapplication.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.SharedPreferenceHelper
import com.example.myapplication.databinding.FragmentOnBoardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardFragment : Fragment() {

    @Inject
    lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        val viewPager2 = binding.viewPager2
        viewPager2.adapter = OnBoardAdapterFragment(this)
    }

    private fun setupListener() {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 3) {
                    binding.btnStart.visibility = View.VISIBLE
                    binding.tvTitle.visibility = View.VISIBLE
                } else {
                    binding.btnStart.visibility = View.INVISIBLE
                    binding.tvTitle.visibility = View.INVISIBLE
                }
            }
        })

        binding.btnStart.setOnClickListener {
            if (binding.viewPager2.currentItem < 4) {
                binding.viewPager2.setCurrentItem(binding.viewPager2.currentItem + 3, true)
            }
        }

        binding.btnStart.setOnClickListener {
            sharedPreferenceHelper.setOnBoardShown()
            findNavController().navigate(R.id.action_onBoardFragment_to_loveCalculatorFragment)
        }
    }
}