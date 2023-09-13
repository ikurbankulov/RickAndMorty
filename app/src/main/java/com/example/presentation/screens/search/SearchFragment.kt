package com.example.presentation.screens.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.example.presentation.screens.detail.CharacterDetailFragment
import com.example.presentation.screens.search.adapter.SearchAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupClickListener()
    }

    private fun init() {
        adapter = SearchAdapter()
        binding.recyclerViewCharacters.adapter = adapter
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        viewModel.searchList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupClickListener() {
        adapter.onItemClickListener = { character ->
            val characterDetailFragment =
                CharacterDetailFragment.newInstance(character.id)
            replaceFragment(characterDetailFragment)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.searchCharacters(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    viewModel.searchCharacters(newText)
                }
                return true
            }
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
