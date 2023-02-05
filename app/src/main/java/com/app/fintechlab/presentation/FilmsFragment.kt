package com.app.fintechlab.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.app.fintechlab.R
import com.app.fintechlab.databinding.FragmentFilmsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FilmsFragment : Fragment() {
    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmsStateAdapter: FilmsStateAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = binding.tabLayout
        filmsStateAdapter = FilmsStateAdapter(this)
        viewPager = binding.viewPager2
        viewPager.adapter = filmsStateAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(
                when(position) {
                    0 -> R.string.title_popular
                    else -> R.string.title_favourite
                }
            )
        }.attach()

        for (i in 0..1) {
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null)
                    as TextView
            binding.tabLayout.getTabAt(i)?.customView = textView
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}