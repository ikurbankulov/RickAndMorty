package com.example.presentation.screens.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.screens.detail.CharacterDetailFragment
import com.example.presentation.screens.favourite.adapter.FavouriteAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteBinding is null")

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var adapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupClickListener()
    }

    private fun init(){
        adapter = FavouriteAdapter()
        binding.recyclerViewCharacters.adapter = adapter
        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        viewModel.characterList.observe(viewLifecycleOwner) { characterList ->
            Log.d("FavouriteFragment", characterList.toString())
            adapter.submitList(characterList)
        }
    }


    private fun setupClickListener(){
        adapter.onItemClickListener = { character ->
            val characterDetailFragment = CharacterDetailFragment.newInstance(character.id)
            replaceFragment(characterDetailFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment){
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
        fun newInstance() = FavouriteFragment()
    }

}