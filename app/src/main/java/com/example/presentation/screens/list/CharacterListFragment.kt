package com.example.presentation.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.presentation.screens.detail.CharacterDetailFragment
import com.example.presentation.screens.favourite.FavouriteFragment
import com.example.presentation.screens.list.adapter.CharacterAdapter
import com.example.presentation.screens.search.adapter.SearchAdapter
import com.example.presentation.screens.search.SearchFragment
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterListBinding is null")

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupClickListener()
    }

    private fun init() {
        adapter = CharacterAdapter()
        binding.recyclerViewCharacters.adapter = adapter
        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        viewModel.characterList.observe(viewLifecycleOwner) {

            viewModel.loadCharacterList()
            viewLifecycleOwner.lifecycleScope.launch {
                adapter.submitData(lifecycle, it)
            }

        }
    }

    private fun setupClickListener() {
        adapter.onItemClickListener = { character ->
            val characterDetailFragment =
                CharacterDetailFragment.newInstance(character.id)
            replaceFragment(characterDetailFragment)
        }

        binding.buttonFavorites.setOnClickListener {
            val favouriteFragment = FavouriteFragment.newInstance()
            replaceFragment(favouriteFragment)
        }

        binding.buttonSearch.setOnClickListener {
            val searchFragment = SearchFragment.newInstance()
            replaceFragment(searchFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_up,
                R.anim.slide_out_down,
                R.anim.slide_in_up,
                R.anim.slide_out_down
            )
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
