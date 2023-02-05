package com.app.fintechlab.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.fintechlab.presentation.favouritefilms.FilmsFavouriteFragment
import com.app.fintechlab.presentation.topfilms.FilmsTopFragment

class FilmsStateAdapter(fragment: FilmsFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FilmsTopFragment()
            else -> FilmsFavouriteFragment()
        }
    }

}