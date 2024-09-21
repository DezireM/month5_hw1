package com.example.myapplication.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.R


class OnBoardAdapterFragment (fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int) = OnBoardViewPagerFragment().apply{
        arguments = Bundle().apply{
            putInt(OnBoardViewPagerFragment.ARG_ONBOARD_POSITION, position)
        }
    }
}