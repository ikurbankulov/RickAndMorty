package com.example.presentation.screens.list

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.data.local.database.DataBase
import com.example.data.local.models.CharacterDbModel
import com.example.data.local.models.LocationDbModel
import com.example.domain.models.Character
import com.example.domain.models.Location
import com.example.presentation.screens.detail.CharacterDetailFragment
import com.example.presentation.screens.favourite.FavouriteFragment
import com.example.presentation.screens.list.adapter.RecyclerViewAdapter
import com.example.presentation.screens.search.SearchFragment
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding
        get() = _binding ?: throw RuntimeException("FragmentCharacterListBinding is null")

    private lateinit var viewModel: CharacterListViewModel
    private lateinit var adapter: RecyclerViewAdapter

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
        adapter = RecyclerViewAdapter()
        binding.recyclerViewCharacters.adapter = adapter
        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        viewModel.characterList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupClickListener() {
        adapter.onItemClickListener = { character ->
            val characterDetailFragment = CharacterDetailFragment.newInstance(character.id)
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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CharacterListFragment()
    }
}
