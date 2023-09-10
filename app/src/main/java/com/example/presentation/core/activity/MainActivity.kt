package com.example.presentation.core.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.screens.favourite.FavouriteFragment
import com.example.presentation.screens.list.CharacterListFragment
import com.example.presentation.screens.search.SearchFragment
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException(BINDING_NULL_MESSAGE)

    private lateinit var adapter: ViewPagerAdapter

    private val fragmentList = listOf(
        CharacterListFragment.newInstance(),
        FavouriteFragment.newInstance(),
        SearchFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ViewPagerAdapter(this, fragmentList)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> CHARACTERS_TAB
                1 -> FAVORITES_TAB
                else -> SEARCH_TAB
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val CHARACTERS_TAB = "Characters"
        private const val FAVORITES_TAB = "Favourites"
        private const val SEARCH_TAB = "Search"
        private const val BINDING_NULL_MESSAGE = "ActivityMainBinding is null"
    }
}
