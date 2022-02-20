package com.soongsil.example.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.soongsil.example.ui.fragment.CalendarFragment

class ViewPagerAdapter(fragment:FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment()
    }
}